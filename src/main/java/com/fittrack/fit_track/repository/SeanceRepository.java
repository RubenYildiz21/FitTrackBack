package com.fittrack.fit_track.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fittrack.fit_track.model.Seance;

@Repository
public interface SeanceRepository extends JpaRepository<Seance, Long> {

    @EntityGraph(value = "Seance.blocs.series", type = EntityGraph.EntityGraphType.LOAD)
    List<Seance> findByUserIdAndDateSeanceBetween(Long userId, LocalDateTime startDate, LocalDateTime endDate);

    @Query("SELECT DISTINCT s FROM Seance s " +
           "LEFT JOIN FETCH s.blocs b " +
           "LEFT JOIN FETCH b.series " +
           "WHERE s.user.id = :userId AND s.dateSeance BETWEEN :startDate AND :endDate")
    List<Seance> findSeancesWithBlocsAndSeries(@Param("userId") Long userId,
                                               @Param("startDate") LocalDateTime startDate,
                                               @Param("endDate") LocalDateTime endDate);
}