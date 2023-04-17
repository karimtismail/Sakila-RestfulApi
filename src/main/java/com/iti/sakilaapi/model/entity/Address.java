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
@Table(name = "address", schema = "sakila", indexes = {
        @Index(name = "idx_location", columnList = "location"),
        @Index(name = "idx_fk_city_id", columnList = "city_id")
})
public class Address implements Serializable {
    @Serial
    private static final long serialVersionUID = 5401074482617022120L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id", columnDefinition = "SMALLINT UNSIGNED not null")
    private Integer id;

    @Column(name = "address", nullable = false, length = 50)
    private String address;

    @Column(name = "address2", length = 50)
    private String address2;

    @Column(name = "district", nullable = false, length = 20)
    private String district;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "city_id", nullable = false)
    private City city;

    @Column(name = "postal_code", length = 10)
    private String postalCode;

    @Column(name = "phone", nullable = false, length = 20)
    private String phone;

    @Column(name = "last_update", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdate;

    @OneToMany(mappedBy = "address")
    private Set<Staff> staff = new LinkedHashSet<>();

    @OneToMany(mappedBy = "address")
    private Set<Store> stores = new LinkedHashSet<>();

    @OneToMany(mappedBy = "address")
    private Set<Customer> customers = new LinkedHashSet<>();

    @Column(name = "location", columnDefinition = "GEOMETRY(65535) not null")
    private Object location;

    public Address(Integer id, String address, String address2, String district, String postalCode, String phone, Date lastUpdate) {
        this.id = id;
        this.address = address;
        this.address2 = address2;
        this.district = district;
        this.postalCode = postalCode;
        this.phone = phone;
        this.lastUpdate = lastUpdate;
    }
}