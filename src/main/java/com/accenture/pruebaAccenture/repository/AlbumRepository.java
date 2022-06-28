package com.accenture.pruebaAccenture.repository;

import com.accenture.pruebaAccenture.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Integer> {
}
