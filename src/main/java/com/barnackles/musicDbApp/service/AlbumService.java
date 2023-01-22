package com.barnackles.musicDbApp.service;

import com.barnackles.musicDbApp.dto.AlbumDto;
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
    private final EditionService editionService;

    public List<AlbumDto> findAll() {

//        List<Album> albumsList = albumRepository.findAll();
        List<Album> albumsList = albumRepository.findAllAlbumsInitializeAllAssociations();

        List<AlbumDto> albumsDtoList = albumsList.stream()
                .map(this::convertAlbumToAlbumDto)
                .toList();

        return albumsDtoList;
    }


    private AlbumDto convertAlbumToAlbumDto(Album album) {

        AlbumDto albumDto = new AlbumDto();
        albumDto.setPerformer(album.getPerformer().getProfessionalName());
        albumDto.setTitle(album.getTitle());
        albumDto.setReleaseDate(album.getReleaseDate());
        albumDto.setAlbumEditions(editionService.findAllEditionsByAlbumIdInitializeAllAssociastions(album.getId()));

        return albumDto;
    }

}
