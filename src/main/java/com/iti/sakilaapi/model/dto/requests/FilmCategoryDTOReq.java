package com.iti.sakilaapi.model.dto.requests;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;

@Data
@NoArgsConstructor
@XmlRootElement
public class FilmCategoryDTOReq implements Serializable {
    private String filmTitle;
    private String categoryName;
    private Instant lastUpdate;
}
