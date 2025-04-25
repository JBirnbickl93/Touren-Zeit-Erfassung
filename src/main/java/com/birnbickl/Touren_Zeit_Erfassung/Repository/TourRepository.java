package com.birnbickl.Touren_Zeit_Erfassung.Repository;

import com.birnbickl.Touren_Zeit_Erfassung.Entity.TourEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TourRepository extends JpaRepository<TourEntity, Integer> {
}
