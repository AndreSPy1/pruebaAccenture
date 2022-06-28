package com.accenture.pruebaAccenture.service;

import com.accenture.pruebaAccenture.dto.PermissionDTO;
import com.accenture.pruebaAccenture.dto.embeddable.AlbumUserKeyDTO;

import java.util.List;

public interface IPermissionService {
    PermissionDTO create(PermissionDTO object);
    PermissionDTO get(AlbumUserKeyDTO id);
    PermissionDTO update(PermissionDTO object);
    void delete(AlbumUserKeyDTO id);

    List<Integer> getUsersByAlbumPermission(String permission, int albumId);
}
