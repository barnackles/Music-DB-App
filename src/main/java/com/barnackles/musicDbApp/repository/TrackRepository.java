package com.barnackles.musicDbApp.repository;

import com.barnackles.musicDbApp.model.Track;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface TrackRepository extends JpaRepository<Track, Long> {

}