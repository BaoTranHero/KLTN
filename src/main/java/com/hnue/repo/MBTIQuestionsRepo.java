package com.hnue.repo;

import com.hnue.entity.HollandQuestions;
import com.hnue.entity.MBTIQuestions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MBTIQuestionsRepo extends JpaRepository<MBTIQuestions, Integer> {

}
