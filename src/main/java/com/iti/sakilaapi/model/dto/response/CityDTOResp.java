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
public class CityDTOResp implements Serializable {
    private Integer id;
    private String city;
    private CountryDTOResp country;
    private Instant lastUpdate;
}
