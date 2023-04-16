package com.iti.sakilaapi.model.dto;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
public class FilmCategoryDto implements Serializable {
    private FilmCategoryPKDto filmCategoryPK;
    private Date lastUpdate;
    @Data
    public static class FilmCategoryPKDto implements Serializable {
        private final short filmId;
        private final short categoryId;
    }
}