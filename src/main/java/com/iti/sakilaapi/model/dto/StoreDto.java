package com.iti.sakilaapi.model.dto;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@XmlRootElement
@AllArgsConstructor
@NoArgsConstructor
public class StoreDto implements Serializable {
    private Short id;
//    private String managerName;
    private AddressDto address;
    private Date lastUpdate;
}