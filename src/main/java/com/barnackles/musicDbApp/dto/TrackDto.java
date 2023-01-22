package com.barnackles.musicDbApp.dto;

import lombok.Data;

@Data
public class TrackDto {

    private int trackNumber;
    private String trackTitle;

    private String albumTitle;
    private String edition;
    private String duration;


}
