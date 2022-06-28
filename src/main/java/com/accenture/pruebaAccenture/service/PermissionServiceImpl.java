package com.accenture.pruebaAccenture.service;

import com.accenture.pruebaAccenture.dto.PermissionDTO;
import com.accenture.pruebaAccenture.dto.embeddable.AlbumUserKeyDTO;
import com.accenture.pruebaAccenture.model.Permission;
import com.accenture.pruebaAccenture.model.embeddable.AlbumUserKey;
import com.accenture.pruebaAccenture.payload.ResponseMessage;
import com.accenture.pruebaAccenture.repository.PermissionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl implements IPermissionService{
    @Autowired
    private PermissionRepository permissionRepository;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private ResponseMessage message;
    @Override
    public PermissionDTO create(PermissionDTO object) {
        PermissionDTO permissionDTO = null;
        try{
            Permission permission = mapper.map(object, Permission.class);
            permissionDTO = mapper.map(permissionRepository.save(permission), PermissionDTO.class);
        }catch(Exception e){
            message.setMessage("Error al Guardar Permission");
        }
        return permissionDTO;
    }

    @Override
    public PermissionDTO get(AlbumUserKeyDTO id) {
        PermissionDTO permissionDTO = null;
        try{
            AlbumUserKey albumUserKeyId = mapper.map(id, AlbumUserKey.class);
            Permission permission = permissionRepository.getReferenceById(albumUserKeyId);
            permissionDTO = mapper.map(permission, PermissionDTO.class);
        }catch(Exception e){
            message.setMessage("Error al Obtener Permission");
        }
        return permissionDTO;
    }

    @Override
    public PermissionDTO update(PermissionDTO object) {
        PermissionDTO permissionDTO = null;
        try{
            AlbumUserKey albumUserKeyId = mapper.map(object.getId(), AlbumUserKey.class);
            if(!permissionRepository.existsById(albumUserKeyId)){
                message.setMessage("No existe el Permission");
                throw new Exception();
            }
            try{
                Permission permission = mapper.map(object, Permission.class);
                permission = permissionRepository.save(permission);
                permissionDTO = mapper.map(permission, PermissionDTO.class);
            }catch(Exception e){
                message.setMessage("Error al actualizar permission");
            }
        }catch(Exception e){
            message.setMessage("Error al Obtener permisssion, "+message.getMessage());
        }

        return permissionDTO;
    }

    @Override
    public void delete(AlbumUserKeyDTO id) {
        try{
            AlbumUserKey albumUserKeyId = mapper.map(id, AlbumUserKey.class);
            permissionRepository.deleteById(albumUserKeyId);
        }catch(Exception e){
            message.setMessage("Error al borrar Permission");
        }
    }

    @Override
    public List<Integer> getUsersByAlbumPermission(String permission, int albumId) {
        List<Integer> users = null;
        try{
            if(permission.equals("reading"))
                users = permissionRepository.getUsersByReading(albumId);
            if(permission.equals("writing"))
                users = permissionRepository.getUsersByWriting(albumId);
        }catch(Exception e){
            message.setMessage("Error al encontrar usuarios");
        }
        return users;
    }
}
