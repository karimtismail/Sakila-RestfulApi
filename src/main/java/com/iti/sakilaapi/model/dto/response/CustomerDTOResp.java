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
public class CustomerDTOResp implements Serializable {
    private Integer id;
    private StoreDTOResp store;
    private String firstName;
    private String lastName;
    private String email;
    private AddressDTOResp address;
    private Boolean active;
    private Instant createDate;
    private Instant lastUpdate;
}
