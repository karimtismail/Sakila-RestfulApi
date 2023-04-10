package com.iti.sakilaapi.dto;

import jakarta.ws.rs.core.Link;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.Instant;
import java.util.Date;
import java.util.List;

/**
 * A DTO for the {@link com.iti.sakilaapi.entity.Actor} entity
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class ActorDto implements Serializable {
    private Integer id;
    private String firstName;
    private String lastName;
    private Date lastUpdate;
    private List<Link> links;
    public ActorDto(String firstName, String lastName, Date lastUpdate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.lastUpdate = lastUpdate;
    }
}