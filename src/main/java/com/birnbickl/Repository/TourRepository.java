package com.birnbickl.Repository;

import com.birnbickl.Entity.TourEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TourRepository extends JpaRepository<TourEntity, Integer> {
}
