package com.hnue.service;

import com.hnue.dto.MBTIResponse;
import com.hnue.dto.MBTIResult;
import com.hnue.entity.*;
import com.hnue.repo.MBTICareersRepo;
import com.hnue.repo.MBTIQuestionsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MBTIServices {
    @Autowired
    private MBTIQuestionsRepo mbtiQuestionsRepo;

    @Autowired
    private MBTICareersRepo mbtiCareersRepo;

    public List<MBTIQuestions> getAllMBTIQuestions() {
        return mbtiQuestionsRepo.findAll();
    }

    public List<MBTICareers> getAllMBTICareers() {
        return mbtiCareersRepo.findAll();
    }

    public List<MBTIResult> calculateMBTIResult(List<MBTIResponse> mbtiResponses) {
        Long E = mbtiResponses.stream().filter(x -> x.getRq().getDa().equals("E")).count();
        Long I = mbtiResponses.stream().filter(x -> x.getRq().getDa().equals("I")).count();
        Long S = mbtiResponses.stream().filter(x -> x.getRq().getDa().equals("S")).count();
        Long N = mbtiResponses.stream().filter(x -> x.getRq().getDa().equals("N")).count();
        Long T = mbtiResponses.stream().filter(x -> x.getRq().getDa().equals("T")).count();
        Long F = mbtiResponses.stream().filter(x -> x.getRq().getDa().equals("F")).count();
        Long J = mbtiResponses.stream().filter(x -> x.getRq().getDa().equals("J")).count();
        Long P = mbtiResponses.stream().filter(x -> x.getRq().getDa().equals("P")).count();

        List<MBTIResult> mbtiResults = new ArrayList<>();
        if (E >= I) {
            mbtiResults.add(new MBTIResult("E_I", E, "E"));
        } else {
            mbtiResults.add(new MBTIResult("E_I", I, "I"));
        }
        if (S >= N) {
            mbtiResults.add(new MBTIResult("S_N", S, "S"));
        } else {
            mbtiResults.add(new MBTIResult("S_N", N, "N"));
        }
        if (T >= F) {
            mbtiResults.add(new MBTIResult("T_F", T, "T"));
        } else {
            mbtiResults.add(new MBTIResult("T_F", F, "F"));
        }
        if (J >= P) {
            mbtiResults.add(new MBTIResult("J_P", J, "J"));
        } else {
            mbtiResults.add(new MBTIResult("J_P", P, "P"));
        }
        return mbtiResults;
    }

    public List<MBTICareers> findByGroupContaining(String mbtiMatch) {
        return mbtiCareersRepo.findByGroupContaining(mbtiMatch);
    }
}
