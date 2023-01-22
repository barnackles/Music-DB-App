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

    /**
     * @return List<TrackDto>
     * Returns list of album Dtos with initialized associations.
     */
    public List<AlbumDto> findAll() {

        List<Album> albumsList = albumRepository.findAllAlbumsInitializeAllAssociations();

        return albumsList.stream()
                .map(this::convertAlbumToAlbumDto)
                .toList();
    }

    /**
     * @param album - album
     * @return AlbumDto
     * Converts album to album data transfer object.
     */

    private AlbumDto convertAlbumToAlbumDto(Album album) {

        AlbumDto albumDto = new AlbumDto();
        albumDto.setPerformer(album.getPerformer().getProfessionalName());
        albumDto.setTitle(album.getTitle());
        albumDto.setReleaseDate(album.getReleaseDate());
        albumDto.setAlbumEditions(editionService.findAllEditionsByAlbumIdInitializeAllAssociations(album.getId()));

        return albumDto;
    }

}
