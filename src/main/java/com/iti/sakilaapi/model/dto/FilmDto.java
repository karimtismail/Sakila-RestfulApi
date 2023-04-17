package com.iti.sakilaapi.model.dto;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@XmlRootElement
@NoArgsConstructor
@AllArgsConstructor
@Data
public class FilmDto implements Serializable {
    private Short filmId;
    private String title;
    private String description;
    private Date releaseYear;
    private short rentalDuration;
    private BigDecimal rentalRate;
    private Short length;
    private BigDecimal replacementCost;
    private String rating;
    private String specialFeatures;
    private Date lastUpdate;
    //    private transient List<FilmActorDto> filmActors;
//    private transient List<FilmCategoryDto> filmCategories;
//    private transient List<InventoryDto> inventories;
    private List<FilmActorDto> filmActors;
    private List<FilmCategoryDto> filmCategories;
    private List<InventoryDto> inventories;

}