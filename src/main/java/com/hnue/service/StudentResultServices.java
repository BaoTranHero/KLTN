package com.hnue.service;

import com.hnue.dto.MBTIResponse;
import com.hnue.dto.MBTIResult;
import com.hnue.entity.MBTICareers;
import com.hnue.entity.MBTIQuestions;
import com.hnue.repo.MBTICareersRepo;
import com.hnue.repo.MBTIQuestionsRepo;
import com.hnue.repo.StudentResultRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentResultServices {
    @Autowired
    private StudentResultRepo studentResultRepo;
}
