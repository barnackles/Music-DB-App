package com.barnackles.musicDbApp.service;

import com.barnackles.musicDbApp.dto.TrackDto;
import com.barnackles.musicDbApp.helpermethods.DurationCalculator;
import com.barnackles.musicDbApp.model.Edition;
import com.barnackles.musicDbApp.model.Track;
import com.barnackles.musicDbApp.repository.EditionRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j

public class TrackService {

    private final EditionRepository editionRepository;
    private final DurationCalculator durationCalculator;

    /**
     * @param albumTitle - full album title.
     * @param editionDescription - at least first two characters of release description.
     * @return List<TrackDto>
     * Returns list of track data transfer objects for a given album edition sorted by track number.
     */
    public List<TrackDto> findAllTracksForAlbumEdition(String albumTitle, String editionDescription) {
        Edition persistedEdition = editionRepository.findEditionByAlbumTitleAndEditionDescriptionStartsWithInitializeTracks(albumTitle, editionDescription)
                .orElseThrow(() -> {
                    log.error("Edition with album title: {} and description starting with {} not found", albumTitle, editionDescription);
                    throw new EntityNotFoundException();
                });

        List<Track> trackList = persistedEdition.getTracks();

        return trackList.stream()
        .map(track -> convertTrackToTrackDto(track, persistedEdition.getAlbum().getTitle(), persistedEdition.getDescription()))
        .sorted(Comparator.comparing(TrackDto::getTrackNumber))
        .toList();
    }
    /**
     * @param track - track
     * @param albumTitle - album title
     * @param editionDescription - edition description
     * @return TrackDto
     * Converts track to track data transfer object.
     */
    private TrackDto convertTrackToTrackDto(Track track, String albumTitle, String editionDescription) {
        TrackDto trackDto = new TrackDto();
        trackDto.setTrackNumber(track.getTrackNumber());
        trackDto.setTrackTitle(track.getTitle());
        trackDto.setAlbumTitle(albumTitle);
        trackDto.setEdition(editionDescription);
        trackDto.setDuration(durationCalculator.calculateTrackDuration(track.getDurationInSeconds()));

        return trackDto;
    }

}
