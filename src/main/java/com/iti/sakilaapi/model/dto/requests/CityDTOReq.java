package com.iti.sakilaapi.model.dto.requests;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@XmlRootElement
public class CityDTOReq implements Serializable {
    private String city;
    private CountryDTOReq country;

    public CityDTOReq(String city, CountryDTOReq country) {
        this.city = city;
        this.country = country;
    }
}
