package com.barnackles.musicDbApp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.util.HashSet;

@Entity
@Data
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Length(max = 100)
    @NotBlank
    private String title;
    @NotNull
    private LocalDate releaseDate;
    @ManyToOne(cascade = CascadeType.ALL)
    private Performer performer;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "album", fetch = FetchType.LAZY)
    private HashSet<Edition> editions;

}
