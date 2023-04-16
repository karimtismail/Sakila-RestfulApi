package com.iti.sakilaapi.model.dto;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@XmlRootElement
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CityDto implements Serializable {
    private Short cityId;
    private String city;
    private Date lastUpdate;
    private short countryID;
    private String countryName;
}