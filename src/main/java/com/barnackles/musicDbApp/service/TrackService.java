package com.barnackles.musicDbApp.service;

import com.barnackles.musicDbApp.model.Track;
import com.barnackles.musicDbApp.repository.TrackRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional

public class TrackService {

    private final TrackRepository trackRepository;

    public List<Track> findAllTracksForAlbumEdition(Long editionId) {
        return trackRepository.findTracksByEditionId(editionId);
    }

}
