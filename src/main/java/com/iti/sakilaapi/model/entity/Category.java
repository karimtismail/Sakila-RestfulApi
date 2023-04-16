package com.iti.sakilaapi.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "category", schema = "sakila")
public class Category implements Serializable {
    @Serial
    private static final long serialVersionUID = 1648938034836988303L;
    @Id
    @Column(name = "category_id", columnDefinition = "TINYINT UNSIGNED not null")
    private Short id;

    @Column(name = "name", nullable = false, length = 25)
    private String name;

    @Column(name = "last_update", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdate;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private Set<FilmCategory> filmCategories = new LinkedHashSet<>();

}