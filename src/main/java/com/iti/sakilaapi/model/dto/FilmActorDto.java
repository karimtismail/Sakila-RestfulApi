package com.iti.sakilaapi.model.dto;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
public class FilmActorDto implements Serializable {
    private FilmActorPKDto filmActorPK;
    private Date lastUpdate;
    @Data
    public static class FilmActorPKDto implements Serializable {
        private final short actorId;
        private final short filmId;
    }
}