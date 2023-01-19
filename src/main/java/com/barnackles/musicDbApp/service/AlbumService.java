package com.barnackles.musicDbApp.service;

import com.barnackles.musicDbApp.model.Album;
import com.barnackles.musicDbApp.repository.AlbumRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AlbumService {
    private final AlbumRepository albumRepository;

    public List<Album> findAll() {
        return albumRepository.findAll();
    }


}
