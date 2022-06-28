package com.accenture.pruebaAccenture.model.embeddable;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter @Setter @NoArgsConstructor @EqualsAndHashCode @ToString
public class AlbumUserKey implements Serializable {
    @Column(name = "album_id")
    private int albumId;
    @Column(name = "user_id")
    private int userId;
}
