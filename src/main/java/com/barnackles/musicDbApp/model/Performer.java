package com.barnackles.musicDbApp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Generated;
import org.hibernate.validator.constraints.Length;

@Entity
@Data
public class Performer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Length(max = 100)
    private String professionalName;
    @Length(max = 100)
    private String firstName;
    @Length(max = 100)
    private String middleName;
    @Length(max = 100)
    private String lastName;
}
