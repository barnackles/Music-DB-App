package com.barnackles.musicDbApp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Entity
@Data
public class Track {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Max(999)
    @Min(1)
    private int trackNumber;
    @Length(max = 100)
    @NotBlank
    private String title;
    @NotBlank
    @Min(value = 1)
    @Max(value = 9999)
    private int durationInSeconds;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Edition edition;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Album album;

}
