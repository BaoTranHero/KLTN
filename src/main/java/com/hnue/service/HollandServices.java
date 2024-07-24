package com.hnue.service;

import com.hnue.dto.HollandResponse;
import com.hnue.dto.HollandResult;
import com.hnue.entity.*;
import com.hnue.repo.HollandCareersDescriptionRepo;
import com.hnue.repo.HollandCareersRepo;
import com.hnue.repo.HollandQuestionsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HollandServices {
    @Autowired
    private HollandQuestionsRepo hollandQuestionsRepo;
    @Autowired
    private HollandCareersRepo hollandCareersRepo;
    @Autowired
    private HollandCareersDescriptionRepo hollandCareersDescriptionRepo;

    public List<HollandQuestions> getAllHollandQuestions() {
        return hollandQuestionsRepo.findAll();
    }

    public List<HollandCareers> getAllHollandCareers() {
        return hollandCareersRepo.findAll();
    }

    public List<HollandCareers> findByHollandCode(String hollandCode) {
        return hollandCareersRepo.findByHollandCode(hollandCode);
    }

    public Optional<HollandCareersDescription> findById(String id) {
        return hollandCareersDescriptionRepo.findById(id);
    }

    public List<HollandResult> calculateHollandResult(List<HollandResponse> hollandResponses) {
        HollandResult kyThuat = new HollandResult("kyThuat", hollandResponses.stream().filter(x -> x.getRq().getGroupH().equals("kyThuat")).mapToInt(x -> Integer.parseInt(x.getRq().getDa())).sum());
        HollandResult nghienCuu = new HollandResult("nghienCuu", hollandResponses.stream().filter(x -> x.getRq().getGroupH().equals("nghienCuu")).mapToInt(x -> Integer.parseInt(x.getRq().getDa())).sum());
        HollandResult ngheThuat = new HollandResult("ngheThuat", hollandResponses.stream().filter(x -> x.getRq().getGroupH().equals("ngheThuat")).mapToInt(x -> Integer.parseInt(x.getRq().getDa())).sum());
        HollandResult xaHoi = new HollandResult("xaHoi", hollandResponses.stream().filter(x -> x.getRq().getGroupH().equals("xaHoi")).mapToInt(x -> Integer.parseInt(x.getRq().getDa())).sum());
        HollandResult quanLi = new HollandResult("quanLi", hollandResponses.stream().filter(x -> x.getRq().getGroupH().equals("quanLi")).mapToInt(x -> Integer.parseInt(x.getRq().getDa())).sum());
        HollandResult nghiepVu = new HollandResult("nghiepVu", hollandResponses.stream().filter(x -> x.getRq().getGroupH().equals("nghiepVu")).mapToInt(x -> Integer.parseInt(x.getRq().getDa())).sum());
        List<HollandResult> hollandResults = new ArrayList<>() {{
            add(kyThuat);
            add(nghienCuu);
            add(ngheThuat);
            add(xaHoi);
            add(quanLi);
            add(nghiepVu);
        }};
        List<HollandResult> hollandResultsSorted = hollandResults.stream().sorted(Comparator.comparing(HollandResult::getTotal).reversed()).collect(Collectors.toList());
        return hollandResultsSorted;
    }

    public List<HollandCareersDescription> findHollandCareersDetailByHollandCode(String h1, String h2) {
        return hollandCareersDescriptionRepo.findHollandCodeAndHollandDescriptionByHollandCode(h1, h2);
    }

}
