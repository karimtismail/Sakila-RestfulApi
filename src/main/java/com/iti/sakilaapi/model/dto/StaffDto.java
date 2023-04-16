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
public class StaffDto implements Serializable {
    private Short staffId;
    private String firstName;
    private String lastName;
    private AddressDto address;
    private byte[] picture;
    private String email;
    private StoreDto store;
    private boolean active;
    private String username;
    private String password;
    private Date lastUpdate;
}