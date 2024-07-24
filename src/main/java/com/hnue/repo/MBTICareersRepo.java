package com.hnue.repo;

import com.hnue.entity.HollandCareers;
import com.hnue.entity.MBTICareers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MBTICareersRepo extends JpaRepository<MBTICareers, Integer> {
    List<MBTICareers> findByGroupContaining(String mbtiMatch);
}
