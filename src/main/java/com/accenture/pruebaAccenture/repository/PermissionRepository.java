package com.accenture.pruebaAccenture.repository;

import com.accenture.pruebaAccenture.model.Permission;
import com.accenture.pruebaAccenture.model.embeddable.AlbumUserKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PermissionRepository extends JpaRepository<Permission, AlbumUserKey> {
    @Query(value = "SELECT user_id from PERMISSSION_TABLE where reading = true and album_id = ?1", nativeQuery = true)
    List<Integer> getUsersByReading(int albumId);
    @Query(value = "SELECT user_id from PERMISSSION_TABLE where writing = true and album_id = ?1", nativeQuery = true)
    List<Integer> getUsersByWriting(int albumId);
}
