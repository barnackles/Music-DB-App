package com.barnackles.musicDbApp.service;

import com.barnackles.musicDbApp.dto.TrackDto;
import com.barnackles.musicDbApp.helpermethods.DurationCalculator;
import com.barnackles.musicDbApp.model.Edition;
import com.barnackles.musicDbApp.model.Track;
import com.barnackles.musicDbApp.repository.EditionRepository;
import com.barnackles.musicDbApp.repository.TrackRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j

public class TrackService {

    private final TrackRepository trackRepository;
    private final EditionRepository editionRepository;
    private final DurationCalculator durationCalculator;

    public List<Track> findAllTracksForAlbumEditionById(Long editionId) {
        return trackRepository.findTracksByEditionId(editionId);
    }

    public List<TrackDto> findAllTracksForAlbumEdition(String albumTitle, String editionDescription) {
//       List<Track> trackList = trackRepository.findTracksByAlbumTitleAndEditionDescriptionStartsWith(albumTitle, editionDescription);

        Edition persistedEdition = editionRepository.findEditionByAlbumTitleAndEditionDescriptionStartsWithInitializeTracks(albumTitle, editionDescription)
                .orElseThrow(() -> {
                    log.error("Edition with album title: {} and description starting with {} not found", albumTitle, editionDescription);
                    throw new EntityNotFoundException("Edition not found");
                });


        List<Track> trackList = persistedEdition.getTracks();

                List<TrackDto> trackDtoList = trackList.stream()
                .map(track -> convertTrackToTrackDto(track, persistedEdition.getAlbum().getTitle(), persistedEdition.getDescription()))
                .toList();

        return trackDtoList;
    }

    private TrackDto convertTrackToTrackDto(Track track, String albumTitle, String editionDescription) {
        TrackDto trackDto = new TrackDto();
        trackDto.setTrackNumber(track.getTrackNumber());
        trackDto.setTrackTitle(track.getTitle());
//        trackDto.setAlbumTitle(track.getAlbum().getTitle());
        trackDto.setAlbumTitle(albumTitle);
//        trackDto.setEdition(track.getEdition().getDescription());
        trackDto.setEdition(editionDescription);
        trackDto.setDuration(durationCalculator.calculateTrackDuration(track.getDurationInSeconds()));

        return trackDto;
    }

}
