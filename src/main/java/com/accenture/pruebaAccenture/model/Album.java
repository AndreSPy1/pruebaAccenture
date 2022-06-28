package com.accenture.pruebaAccenture.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "album_table")
@Getter @Setter @NoArgsConstructor @ToString
public class Album {
    @Column(name = "userId")
    private int userId;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "title")
    private String title;
}
