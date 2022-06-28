package com.accenture.pruebaAccenture.dto;

import com.accenture.pruebaAccenture.dto.embeddable.AlbumUserKeyDTO;
import com.accenture.pruebaAccenture.model.Album;
import com.accenture.pruebaAccenture.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @NoArgsConstructor @ToString
public class PermissionDTO {
    private AlbumUserKeyDTO id;
    @JsonIgnore
    private AlbumDTO album;
    @JsonIgnore
    private UserDTO user;
    private boolean reading;
    private boolean writing;
}
