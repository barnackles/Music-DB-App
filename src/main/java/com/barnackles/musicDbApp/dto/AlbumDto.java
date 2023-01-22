package com.barnackles.musicDbApp.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class AlbumDto {

    private String performer;
    private String title;
    private LocalDate releaseDate;
    private List<EditionDto> albumEditions;




}
