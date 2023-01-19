package com.barnackles.musicDbApp.repository;

import com.barnackles.musicDbApp.model.Track;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface TrackRepository extends JpaRepository<Track, Long> {

    List<Track> findTracksByEditionId(Long editionId);

}
