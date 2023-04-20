package com.iti.sakilaapi.model.dto.response;

import jakarta.ws.rs.core.Link;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
public class FilmCategoryDTOResp implements Serializable {
    private FilmCategoryIdDTOResp id;
    private FilmDTOResp film;
    private CategoryDTOResp category;
    private Instant lastUpdate;
    private List<Link> links;
}
