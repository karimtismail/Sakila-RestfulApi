package com.iti.sakilaapi.model.dto.requests;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;

@Data
@NoArgsConstructor
@XmlRootElement
public class CityDTOReq implements Serializable {
    private String city;
    private Integer countryId;
    private Instant lastUpdate;

    public CityDTOReq(String city, Integer countryId) {
        this.city = city;
        this.countryId = countryId;
    }
}
