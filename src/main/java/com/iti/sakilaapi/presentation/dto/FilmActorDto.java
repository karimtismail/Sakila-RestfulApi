package com.iti.sakilaapi.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.Instant;

/**
 * A DTO for the {@link com.iti.sakilaapi.data.entity.FilmActor} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class FilmActorDto implements Serializable {
    private Instant lastUpdate;
}