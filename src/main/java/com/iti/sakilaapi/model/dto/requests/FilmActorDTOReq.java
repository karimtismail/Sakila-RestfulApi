package com.iti.sakilaapi.model.dto.requests;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;

@Data
@NoArgsConstructor
@XmlRootElement
public class FilmActorDTOReq implements Serializable {
    private String firstName;
    private String title;
    private Instant lastUpdate;
}
