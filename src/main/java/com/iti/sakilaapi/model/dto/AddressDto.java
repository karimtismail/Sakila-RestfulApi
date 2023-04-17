package com.iti.sakilaapi.model.dto;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@XmlRootElement
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AddressDto implements Serializable {
    private Short addressId;
    private String address;
    private String address2;
    private String district;
    private String postalCode;
    private String phone;
    private Date lastUpdate;

    public AddressDto(String address, String address2, String district, String postalCode, String phone, Date lastUpdate) {
        this.address = address;
        this.address2 = address2;
        this.district = district;
        this.postalCode = postalCode;
        this.phone = phone;
        this.lastUpdate = lastUpdate;
    }
}