package com.barnackles.musicDbApp.service;

import com.barnackles.musicDbApp.dto.EditionDto;
import com.barnackles.musicDbApp.helpermethods.DurationCalculator;
import com.barnackles.musicDbApp.model.Edition;
import com.barnackles.musicDbApp.model.Track;
import com.barnackles.musicDbApp.repository.EditionRepository;
import com.barnackles.musicDbApp.repository.TrackRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class EditionService {

    private final EditionRepository editionRepository;

    private final DurationCalculator durationCalculator;


    public List<EditionDto> findAllEditionsByAlbumId(Long albumId) {
        List<Edition> listOfEditions = editionRepository.findEditionsByAlbumId(albumId);
        List<EditionDto> listOfEditionDtos = listOfEditions.stream()
                .map(this::convertEditionToEditionDto)
                .toList();

        return listOfEditionDtos;
    }

    public List<EditionDto> findAllEditionsByAlbumIdInitializeAllAssociastions(Long albumId) {
        List<Edition> listOfEditions = editionRepository.findEditionsByAlbumIdInitializeAllAssociations(albumId);
        List<EditionDto> listOfEditionDtos = listOfEditions.stream()
                .map(this::convertEditionToEditionDto)
                .toList();

        return listOfEditionDtos;
    }

    private EditionDto convertEditionToEditionDto(Edition edition) {

        EditionDto editionDto = new EditionDto();
        editionDto.setDescription(edition.getDescription());

        editionDto.setDuration(durationCalculator.calculateEditionDuration(edition.getTracks()));

        return editionDto;
    }





}
