package com.iti.sakilaapi.model.dto.requests;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement
public class ActorDTOReq implements Serializable {
    private String firstName;
    private String lastName;
    private Instant lastUpdate;
}
