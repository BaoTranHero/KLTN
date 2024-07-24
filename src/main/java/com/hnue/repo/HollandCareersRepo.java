package com.hnue.repo;

import com.hnue.entity.HollandCareers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HollandCareersRepo extends JpaRepository<HollandCareers, String> {
    List<HollandCareers> findByHollandCode(String hollandCode);
}
