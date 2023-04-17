package com.iti.sakilaapi.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "film_actor", schema = "sakila", indexes = {
        @Index(name = "idx_fk_film_id", columnList = "film_id")
})
public class FilmActor implements Serializable {
    @Serial
    private static final long serialVersionUID = 5663892245495256529L;
    @EmbeddedId
    private FilmActorId id;

    @MapsId("actorId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "actor_id", nullable = false)
    @JsonBackReference
    private Actor actor;

    @MapsId("filmId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "film_id", nullable = false)
//    @JsonBackReference
    private Film film;

    @Column(name = "last_update", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdate;

    public FilmActor(FilmActorId id, Actor actor, Film film, Date lastUpdate) {
        this.id = new FilmActorId(actor.getId(), film.getId());
        this.actor = actor;
        this.film = film;
        this.lastUpdate = lastUpdate;
    }
}