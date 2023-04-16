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
@Table(name = "language", schema = "sakila")
public class Language implements Serializable {
    @Serial
    private static final long serialVersionUID = -95848265263623310L;

    @Id
    @Column(name = "language_id", columnDefinition = "TINYINT UNSIGNED not null")
    private Short id;

    @Column(name = "name", nullable = false, length = 20)
    private String name;

    @Column(name = "last_update", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdate;

    @OneToMany(mappedBy = "language", cascade = CascadeType.ALL)
    private Set<Film> language_id_films = new LinkedHashSet<>();

    @OneToMany(mappedBy = "originalLanguage", cascade = CascadeType.ALL)
    private Set<Film> orifinal_langauge_id_films = new LinkedHashSet<>();

}