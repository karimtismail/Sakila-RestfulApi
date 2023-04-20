package com.iti.sakilaapi.model.dto.requests;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;

@Data
@NoArgsConstructor
@XmlRootElement
public class StaffDTOReq implements Serializable {
    private String firstName;
    private String lastName;
    private String address;
    private byte[] picture;
    private String email;
    private Integer storeId;
    private Boolean active;
    private String username;
    private String password;
    private Instant lastUpdate;
}
