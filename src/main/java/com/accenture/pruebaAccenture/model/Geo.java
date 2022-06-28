package com.accenture.pruebaAccenture.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "geo_table")
@Getter @Setter @NoArgsConstructor @ToString
public class Geo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "lat")
    private String lat;
    @Column(name = "lng")
    private String lng;
    @OneToOne(mappedBy = "geo", cascade = CascadeType.ALL)
    private Address address;
}
