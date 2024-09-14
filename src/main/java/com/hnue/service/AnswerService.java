package com.hnue.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hnue.dto.*;
import com.hnue.entity.*;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.service.OpenAiService;
import org.apache.commons.text.similarity.CosineSimilarity;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AnswerService {

    public String SYSTEM_TASK_MESSAGE =
            "Nothing";

    public String SYSTEM_TASK_LO_TRINH =
            "Nothing";

    @Autowired
    private HollandServices hollandServices;
    @Autowired
    private MBTIServices mbtiServices;

    private static final String KEY = "NOTHING_TO_SHOW_HERE";
    public static OpenAiService openAiService = new OpenAiService(KEY, Duration.ofSeconds(60));


    public List returnResultFromMCQs(Answers answers) throws JsonProcessingException {
        List<HollandResult> hollandResults = hollandServices.calculateHollandResult(answers.hollandAnswer);
        List<HollandResult> hollandCode = hollandServices.calculateHollandResult(answers.hollandAnswer);
        hollandCode.forEach(x -> {
            if (x.get_id().equals("kyThuat")) {
                x.set_id("Realistic");
            } else if (x.get_id().equals("nghienCuu")) {
                x.set_id("Investigate");
            } else if (x.get_id().equals("ngheThuat")) {
                x.set_id("Artistic");
            } else if (x.get_id().equals("xaHoi")) {
                x.set_id("Social");
            } else if (x.get_id().equals("quanLi")) {
                x.set_id("Enterprising");
            } else if (x.get_id().equals("nghiepVu")) {
                x.set_id("Conventional");
            }
        });
        String hollandMatch1 = hollandCode.get(0).get_id().substring(0, 1) + hollandCode.get(1).get_id().substring(0, 1) + hollandCode.get(2).get_id().substring(0, 1);
        String hollandMatch2 = hollandMatch1.substring(0, hollandMatch1.length() - 1) + 'X';
        List<HollandCareers> hollandCareersMatch = new ArrayList<>();
        hollandCareersMatch.addAll(hollandServices.findByHollandCode(hollandMatch1));
        hollandCareersMatch.addAll(hollandServices.findByHollandCode(hollandMatch2));


        List<MBTIResult> mbtiResults = mbtiServices.calculateMBTIResult(answers.mbtiAnswer);
        String mbtiMatch = "";
        for (MBTIResult x : mbtiResults) {
            mbtiMatch += x.getVal();
        }
        List<MBTICareers> mbtiCareersMatch = mbtiServices.findByGroupContaining(mbtiMatch);

        List<CareersResponse> matchingCareersByHollandAndMBTI = getMatchingCareersByHollandAndMBTI(hollandCareersMatch, mbtiCareersMatch);
        matchingCareersByHollandAndMBTI.forEach(x -> {
            Optional<HollandCareersDescription> byTitle = hollandServices.findById(x.getId());
            byTitle.ifPresent(hollandCareersDescription -> x.setMoTa(hollandCareersDescription.getDescription()));
        });

        String DANH_SACH_NGHE = "";
        String MO_TA_NGHE = "";
        for (CareersResponse careersResponse : matchingCareersByHollandAndMBTI) {
            DANH_SACH_NGHE += careersResponse.getNgheNghiep() + ",";
            MO_TA_NGHE += careersResponse.getMoTa() + ",";
        }
        ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest
                .builder()
                .model("gpt-3.5-turbo")
                .temperature(0.5)
                .messages(
                        List.of(
                                new ChatMessage("system", SYSTEM_TASK_MESSAGE),
                                new ChatMessage("user", "Các ngành nghề đó là " + DANH_SACH_NGHE)))
                .build();

        StringBuilder builder = new StringBuilder();
        openAiService.createChatCompletion(chatCompletionRequest)
                .getChoices().forEach(choice -> {
                    builder.append(choice.getMessage().getContent());
                });

        List<List> result = getLists(builder, mbtiResults, hollandResults);
        return result;
    }

    public Suggestion getSuggestionBaseOnCareerAndScore(Career career) throws JsonProcessingException {
        String prompt = "Điểm số của tôi là: Toán = " + career.getDToan() + ", Văn = " + career.getDVan() + ", Anh = " + career.getDVan() + ", nghề nghiệp mong muốn là: " + career.getTenNN();
        ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest
                .builder()
                .model("gpt-4-turbo-2024-04-09")
                .temperature(0.5)
                .messages(
                        List.of(
                                new ChatMessage("system", SYSTEM_TASK_LO_TRINH),
                                new ChatMessage("user", prompt)))
                .build();

        StringBuilder builder = new StringBuilder();
        openAiService.createChatCompletion(chatCompletionRequest)
                .getChoices().forEach(choice -> {
                    builder.append(choice.getMessage().getContent());
                });

        String s = builder.toString().replaceAll("\n\r", " ").replaceAll("\\u0009", " ");
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<Suggestion> typeReference = new TypeReference<>() {
        };
        return objectMapper.readValue(s, typeReference);
    }

    public static List<CareersResponse> mappingCareers(StringBuilder builder) throws JsonProcessingException {
        String s = builder.toString().replaceAll("\n\r", " ").replaceAll("\\u0009", " ");
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<CareersResponse>> typeReference = new TypeReference<List<CareersResponse>>() {
        };
        return objectMapper.readValue(s, typeReference);
    }

    @NotNull
    private static List<List> getLists(StringBuilder builder, List<MBTIResult> mbtiResults, List<HollandResult> hollandResults) throws JsonProcessingException {
        List<List> result = new ArrayList<>() {{
            add(mbtiResults);
            add(hollandResults);
            add(mappingCareers(builder));
        }};
        return result;
    }

    public static List<CareersResponse> getMatchingCareersByHollandAndMBTI(List<HollandCareers> hollandCareers, List<MBTICareers> mbtiCareers) {

        List<CareersResponse> careersResponses = new ArrayList<>();
        CosineSimilarity cs = new CosineSimilarity();
        for (HollandCareers i : hollandCareers) {
            for (MBTICareers j : mbtiCareers) {
                String textDelimiter = " ";
                String documentA = i.getTitle();
                String documentB = j.getTitle();
                Map<CharSequence, Integer> vectorA = Arrays.stream(documentA.split(textDelimiter)).collect(Collectors.toMap(character -> character, character -> 1, Integer::sum));
                Map<CharSequence, Integer> vectorB = Arrays.stream(documentB.split(textDelimiter)).collect(Collectors.toMap(character -> character, character -> 1, Integer::sum));
                Double v = cs.cosineSimilarity(vectorA, vectorB);
                if (v >= 0.6) {
                    careersResponses.add(new CareersResponse(i.getId(), j.getTitle(), j.getDescription(), null, null, null));
                }
            }
        }
        careersResponses = careersResponses.stream().distinct().collect(Collectors.toList());
        return careersResponses;
    }


}
