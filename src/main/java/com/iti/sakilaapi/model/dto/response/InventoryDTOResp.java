package com.iti.sakilaapi.model.dto.response;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.*;

import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
public class InventoryDTOResp implements Serializable {
    private Integer id;
    private FilmDTOResp film;
    private StoreDTOResp store;
    private Instant lastUpdate;
}
