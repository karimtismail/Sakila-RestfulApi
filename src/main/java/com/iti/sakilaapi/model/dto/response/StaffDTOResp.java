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
public class StaffDTOResp implements Serializable {
    private Short id;
    private String firstName;
    private String lastName;
    private AddressDTOResp address;
    private byte[] picture;
    private String email;
    private StoreDTOResp store;
    private Boolean active = false;
    private String username;
    private String password;
    private Instant lastUpdate;
}
