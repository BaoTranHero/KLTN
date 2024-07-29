package com.hnue.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hnue.dto.Answers;
import com.hnue.dto.Career;
import com.hnue.dto.MBTICareersDescription;
import com.hnue.dto.Suggestion;
import com.hnue.entity.*;
import com.hnue.service.AnswerService;
import com.hnue.service.HollandServices;
import com.hnue.service.MBTIServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class QuestionController {

    @Autowired
    private HollandServices hollandServices;
    @Autowired
    private MBTIServices mbtiServices;
    @Autowired
    private AnswerService answerService;

    @GetMapping("/HollAnd/data")
    public List<HollandQuestions> getAllHollandQuestions() {
        return hollandServices.getAllHollandQuestions();
    }

    @GetMapping("/MBTI/data")
    public List<MBTIQuestions> getAllMBTIQuestions() {
        return mbtiServices.getAllMBTIQuestions();
    }

    @PostMapping("/request/combined")
    public List calculateResult(@RequestBody Answers answers) throws JsonProcessingException {
        return answerService.returnResultFromMCQs(answers);
    }

    @GetMapping("/search/holland/{h1}/{h2}/{h3}")
    public List<HollandCareersDescription> findAll(@PathVariable String h1, @PathVariable String h2, @PathVariable String h3) {
        return hollandServices.findHollandCareersDetailByHollandCode(h1 + h2 + h3, h1 + h2 + "X");
    }

    @GetMapping("/search/mbti/{mbti}")
    public List<MBTICareersDescription> findAll(@PathVariable String mbti) {
        List<MBTICareers> byGroupContaining = mbtiServices.findByGroupContaining(mbti);
        List<MBTICareersDescription> MBTICareersByMBTI = new ArrayList<>();
        byGroupContaining.forEach(x -> {
            MBTICareersByMBTI.add(new MBTICareersDescription(x.getId(), x.getTitle(), x.getDescription()));
        });
        return MBTICareersByMBTI;
    }

    @PostMapping("/api/tuvan")
    public Suggestion getDetailSuggestion(@RequestBody Career career) throws JsonProcessingException {
        return answerService.getSuggestionBaseOnCareerAndScore(career);
    }

}