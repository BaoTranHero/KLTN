package com.hnue.repo;

import com.hnue.entity.HollandQuestions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HollandQuestionsRepo extends JpaRepository<HollandQuestions, Integer> {

}
