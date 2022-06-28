package com.accenture.pruebaAccenture.util;

import com.accenture.pruebaAccenture.dto.AlbumDTO;
import com.accenture.pruebaAccenture.dto.PermissionDTO;
import com.accenture.pruebaAccenture.dto.UserDTO;
import com.accenture.pruebaAccenture.dto.embeddable.AlbumUserKeyDTO;
import com.accenture.pruebaAccenture.payload.ResponseMessage;
import com.accenture.pruebaAccenture.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RestControllerUtil {
    @Autowired
    private IUserService userService;
    @Autowired
    private ResponseMessage message;
    public AlbumUserKeyDTO buildAlbumUserKey(int userId, int albumId){
        AlbumUserKeyDTO albumUserKeyId = new AlbumUserKeyDTO();
        albumUserKeyId.setUserId(userId);
        albumUserKeyId.setAlbumId(albumId);
        return albumUserKeyId;
    }

    public PermissionDTO buildPermission(AlbumUserKeyDTO id, AlbumDTO albumDTO, UserDTO userDTO,
                                         boolean reading, boolean writing){
        PermissionDTO permissionDTO = new PermissionDTO();
        permissionDTO.setId(id);
        permissionDTO.setAlbum(albumDTO);
        permissionDTO.setUser(userDTO);
        permissionDTO.setReading(reading);
        permissionDTO.setWriting(writing);
        return permissionDTO;
    }

    public List<UserDTO> getUsers(List<Integer> usersId){
        List<UserDTO> users = new ArrayList<>();
        try{
            for (Integer userId: usersId) {
                users.add(userService.get(userId));
            }
        }catch(Exception e){
            message.setMessage("Controller Util, "+message.getMessage());
        }

        return users;
    }
}
