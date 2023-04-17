package com.iti.sakilaapi.model.dto;

import com.iti.sakilaapi.model.entity.Actor;
import jakarta.ws.rs.core.Link;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@XmlRootElement
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActorDto implements Serializable {
    private Short actorId;
    private String firstName;
    private String lastName;
    private Date lastUpdate;
    private List<Link> links;
    //    @JsonbTransient
    private List<FilmActorDto> films;

    public ActorDto(Integer id, String firstName, String lastName, Date lastUpdate) {
    }

    public ActorDto(String firstName, String lastName, Date lastUpdate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.lastUpdate = lastUpdate;
    }

    public ActorDto(Actor actor) {
    }
}