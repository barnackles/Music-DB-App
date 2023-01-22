package com.barnackles.musicDbApp.controller;

import com.barnackles.musicDbApp.dto.AlbumDto;
import com.barnackles.musicDbApp.dto.TrackDto;

import com.barnackles.musicDbApp.service.AlbumService;
import com.barnackles.musicDbApp.service.TrackService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Length;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@Validated
public class MusicAppController {

    private final AlbumService albumService;
    private final TrackService trackService;

    /**
     * @return ResponseEntity<List<AlbumDto>>
     * Returns list of albums with their respective editions and total length calculated from edition track listing.
     */
    @GetMapping("/albums")
    public ResponseEntity<List<AlbumDto>> getAllAlbums() {

        List<AlbumDto> listOfAlbumsDtos = albumService.findAll();

        return new ResponseEntity<>(listOfAlbumsDtos, HttpStatus.OK);

    }

    /**
     * @param albumTitle - full album title
     * @param editionDescription - at least first two characters of edition description eg. "orig" for original release.
     * @return ResponseEntity<List<TrackDto>>
     * Returns tracklist for a given album edition ordered by track number.
     */

    @GetMapping("/tracklist/{albumTitle}/{editionDescription}")

    public ResponseEntity<List<TrackDto>> getEditionTrackList(@PathVariable @Length(min = 2, max = 100, message = "Album title length must be between 2 and 100 characters") String albumTitle,
                                                              @PathVariable @Length(min = 2, max = 100, message = "Edition description length must be between 2 and 100 characters") String editionDescription) {

        log.info("title: {}, desc: {}", albumTitle, editionDescription);
        List<TrackDto> tracklist = trackService.findAllTracksForAlbumEdition(albumTitle, editionDescription);

        return new ResponseEntity<>(tracklist, HttpStatus.OK);

    }





}
