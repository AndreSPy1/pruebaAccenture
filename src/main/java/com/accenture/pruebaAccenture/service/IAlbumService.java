package com.accenture.pruebaAccenture.service;

import com.accenture.pruebaAccenture.dto.AlbumDTO;

import java.util.List;

public interface IAlbumService {
    AlbumDTO create(AlbumDTO object);
    AlbumDTO get(int id);
    AlbumDTO update(AlbumDTO object);
    void delete(int id);
    List<AlbumDTO> getAll();
}
