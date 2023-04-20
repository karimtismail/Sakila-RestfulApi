package com.iti.sakilaapi.model.dto.requests;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;

@Data
@NoArgsConstructor
@XmlRootElement
public class CountryDTOReq implements Serializable {
    private String country;
    private Instant lastUpdate;

    public CountryDTOReq(String country) {
        this.country = country;
    }
}
