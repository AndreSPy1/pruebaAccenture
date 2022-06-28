package com.accenture.pruebaAccenture.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @NoArgsConstructor @ToString
public class UsersPermissionSpecificByAlbumRequestBody {
    private String permission;
    private int albumId;
}
