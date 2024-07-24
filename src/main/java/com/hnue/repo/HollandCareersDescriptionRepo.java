package com.hnue.repo;

import com.hnue.entity.HollandCareersDescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HollandCareersDescriptionRepo extends JpaRepository<HollandCareersDescription, String> {
    Optional<HollandCareersDescription> findById(String id);

    @Query(value = "SELECT h.ID, h.Title, d.Description" +
            " FROM HollandCareers h" +
            " JOIN HollandCareersDescription d" +
            " ON H.ID = d.ID" +
            " WHERE h.Holland_Code LIKE :h1" +
            " OR h.Holland_Code LIKE :h2", nativeQuery = true)
    List<HollandCareersDescription> findHollandCodeAndHollandDescriptionByHollandCode(@Param("h1") String h1, @Param("h2") String h2);

}
