package com.birnbickl.Touren_Zeit_Erfassung.Repository;

import com.birnbickl.Touren_Zeit_Erfassung.Entity.UserEntity;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername(String username);
}
