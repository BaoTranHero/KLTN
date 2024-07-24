package com.hnue.repo;

import com.hnue.entity.MBTICareers;
import com.hnue.entity.StudentResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentResultRepo extends JpaRepository<StudentResult, Integer> {
}
