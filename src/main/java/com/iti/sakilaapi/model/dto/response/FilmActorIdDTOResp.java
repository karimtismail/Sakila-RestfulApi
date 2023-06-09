package com.iti.sakilaapi.model.dto.response;

import jakarta.ws.rs.core.Link;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
public class FilmActorIdDTOResp implements Serializable {
    private Integer actorId;
    private Integer filmId;
    private List<Link> links;
}
