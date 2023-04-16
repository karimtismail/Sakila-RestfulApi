package com.iti.sakilaapi.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "film_text", schema = "sakila", indexes = {@Index(name = "idx_title_description", columnList = "title, description")})
public class FilmText implements Serializable {
    @Serial
    private static final long serialVersionUID = 8424959740296459358L;

    @Id
    @Column(name = "film_id", nullable = false)
    private Short id;

    @Column(name = "title", nullable = false)
    private String title;

    @Lob
    @Column(name = "description")
    private String description;
}