package com.barnackles.musicDbApp.controller;

import com.barnackles.musicDbApp.dto.AlbumDto;
import com.barnackles.musicDbApp.model.Album;
import com.barnackles.musicDbApp.model.Track;
import com.barnackles.musicDbApp.service.AlbumService;
import com.barnackles.musicDbApp.service.TrackService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor

public class MusicAppController {

    private final AlbumService albumService;
    private final TrackService trackService;

    @GetMapping("/albums")
    public ResponseEntity<List<AlbumDto>> getAllAlbums() {

        List<AlbumDto> listOfAlbumsDtos = albumService.findAll();

        return new ResponseEntity<>(listOfAlbumsDtos, HttpStatus.OK);

    }

    @GetMapping("/tracklist/{editionId}")
    public ResponseEntity<List<Track>> getAllAlbums(@PathVariable Long editionId) {

        return new ResponseEntity<>(trackService.findAllTracksForAlbumEdition(editionId), HttpStatus.OK);

    }





}
