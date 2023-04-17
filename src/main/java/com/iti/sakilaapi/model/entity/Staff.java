package com.iti.sakilaapi.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
@Table(name = "staff", schema = "sakila", indexes = {
        @Index(name = "idx_fk_store_id", columnList = "manager_staff_id"),
        @Index(name = "idx_fk_address_id", columnList = "address_id")
})
public class Staff implements Serializable {
    @Serial
    private static final long serialVersionUID = 3112450445982800587L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "staff_id", columnDefinition = "TINYINT UNSIGNED not null")
    private Short id;

    @Column(name = "first_name", nullable = false, length = 45)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 45)
    private String lastName;

    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    @Column(name = "picture")
    private byte[] picture;

    @Column(name = "email", length = 50)
    private String email;

    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "manager_staff_id", nullable = false, referencedColumnName = "manager_staff_id")
    private Store store;

    @Column(name = "active", nullable = false)
    private Boolean active = false;

    @Column(name = "username", nullable = false, length = 16)
    private String username;

    @Column(name = "password", length = 40)
    private String password;

    @Column(name = "last_update", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdate;

    @OneToMany(mappedBy = "staff", cascade = CascadeType.ALL)
    private List<Payment> payments = new ArrayList<>();

    @OneToOne(mappedBy = "managerStaff", cascade = CascadeType.ALL)
    private Store manager_staff_id_store;

    @OneToMany(mappedBy = "staff", cascade = CascadeType.ALL)
    private List<Rental> rentals = new ArrayList<>();
}