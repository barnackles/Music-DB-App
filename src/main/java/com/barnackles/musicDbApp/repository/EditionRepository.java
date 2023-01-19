package com.barnackles.musicDbApp.repository;

import com.barnackles.musicDbApp.model.Edition;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface EditionRepository extends JpaRepository<Edition, Long> {
}
