package com.barnackles.musicDbApp.service;

import com.barnackles.musicDbApp.dto.TrackDto;
import com.barnackles.musicDbApp.helpermethods.DurationCalculator;
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
    private final DurationCalculator durationCalculator;

    public List<Track> findAllTracksForAlbumEdition(Long editionId) {
        return trackRepository.findTracksByEditionId(editionId);
    }

    public List<TrackDto> findAllTracksForAlbumEdition(String albumTitle, String editionDescription) {
        List<Track> trackList = trackRepository.findTracksByAlbumTitleAndEditionDescriptionStartsWith(albumTitle, editionDescription);
        List<TrackDto> trackDtoList = trackList.stream()
                .map(this::convertTrackToTrackDto)
                .toList();

        return trackDtoList;
    }

    private TrackDto convertTrackToTrackDto(Track track) {
        TrackDto trackDto = new TrackDto();
        trackDto.setTrackNumber(track.getTrackNumber());
        trackDto.setTrackTitle(track.getTitle());
        trackDto.setAlbumTitle(track.getAlbum().getTitle());
        trackDto.setEdition(track.getEdition().getDescription());
        trackDto.setDuration(durationCalculator.calculateTrackDuration(track.getDurationInSeconds()));

        return trackDto;
    }

}
