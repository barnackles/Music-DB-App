package com.barnackles.musicDbApp.repository;

import com.barnackles.musicDbApp.model.Edition;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface EditionRepository extends JpaRepository<Edition, Long> {

    List<Edition> findEditionsByAlbumId(Long albumId);
}
