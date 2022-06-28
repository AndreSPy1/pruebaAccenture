package com.accenture.pruebaAccenture.model;

import com.accenture.pruebaAccenture.model.embeddable.AlbumUserKey;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "permisssion_table")
@Getter @Setter @NoArgsConstructor @ToString
public class Permission {
    @EmbeddedId
    private AlbumUserKey id;

    @ManyToOne
    @MapsId("albumId")
    @JoinColumn(name = "album_id")
    private Album album;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "reading")
    private boolean reading;

    @Column(name = "writing")
    private boolean writing;
}
