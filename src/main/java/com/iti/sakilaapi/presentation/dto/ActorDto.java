package com.iti.sakilaapi.presentation.dto;

import jakarta.ws.rs.core.Link;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * A DTO for the {@link com.iti.sakilaapi.data.entity.Actor} entity
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