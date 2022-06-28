package com.accenture.pruebaAccenture.dto.embeddable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @NoArgsConstructor @ToString
public class AlbumUserKeyDTO {
    private int albumId;
    private int userId;
}
