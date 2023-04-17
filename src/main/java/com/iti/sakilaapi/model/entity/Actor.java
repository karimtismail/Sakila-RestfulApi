package com.iti.sakilaapi.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "actor", schema = "sakila", indexes = {
        @Index(name = "idx_actor_last_name", columnList = "last_name")
})
public class Actor implements Serializable {
    @Serial
    private static final long serialVersionUID = -1136385676474999212L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "actor_id", columnDefinition = "SMALLINT UNSIGNED not null")
    private Integer id;

    @Column(name = "first_name", nullable = false, length = 45)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 45)
    private String lastName;

    @Column(name = "last_update", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdate;

    @OneToMany(mappedBy = "actor", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<FilmActor> films = new ArrayList<>();

    public Actor(String firstName, String lastName, Date lastUpdate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.lastUpdate = lastUpdate;
    }

    public Actor(Integer id, String firstName, String lastName, Date lastUpdate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.lastUpdate = lastUpdate;
    }
}