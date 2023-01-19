package com.barnackles.musicDbApp.repository;

import com.barnackles.musicDbApp.model.Album;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface AlbumRepository extends JpaRepository<Album, Long> {
}
