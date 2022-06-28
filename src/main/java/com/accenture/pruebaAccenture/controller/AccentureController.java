package com.accenture.pruebaAccenture.controller;

import com.accenture.pruebaAccenture.dto.*;
import com.accenture.pruebaAccenture.dto.embeddable.AlbumUserKeyDTO;
import com.accenture.pruebaAccenture.payload.*;
import com.accenture.pruebaAccenture.service.IAlbumService;
import com.accenture.pruebaAccenture.service.IPermissionService;
import com.accenture.pruebaAccenture.service.IUserService;
import com.accenture.pruebaAccenture.util.RestClientUtil;
import com.accenture.pruebaAccenture.util.RestControllerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AccentureController {

    @Autowired
    private RestClientUtil restClientUtil;
    @Autowired
    private RestControllerUtil restControllerUtil;
    @Autowired
    private IAlbumService albumService;
    @Autowired
    private IUserService userService;
    @Autowired
    private IPermissionService permissionService;
    @Autowired
    private ResponseMessage responseMessage;

    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getUsers() throws IOException {
        List<UserDTO> resultListUser = restClientUtil.getUsers();
        return ResponseEntity.ok(resultListUser);
    }

    @GetMapping("/photos")
    public ResponseEntity<List<PhotoDTO>> getPhotos() throws IOException {
        List<PhotoDTO> resultListPhoto = restClientUtil.getPhotos();
        return ResponseEntity.ok(resultListPhoto);
    }

    @GetMapping("/albums")
    public ResponseEntity<List<AlbumDTO>> getAlbums() throws IOException {
        List<AlbumDTO> resultListAlbum = restClientUtil.getAlbums();
        return ResponseEntity.ok(resultListAlbum);
    }

    @GetMapping("/users/{id}/albums")
    public ResponseEntity<List<AlbumDTO>> getAlbumsPerUser(
            @PathVariable("id") int id) throws IOException {
        List<AlbumDTO> resultListAlbum = restClientUtil.getAlbumsPerUser(id);
        return ResponseEntity.ok(resultListAlbum);
    }

    @GetMapping("/users/{id}/photos")
    public ResponseEntity<List<PhotoDTO>> getPhotosPerUser(
            @PathVariable("id") int id) throws IOException {
        List<PhotoDTO> resultListPhotosPerUser = restClientUtil.getPhotosPerAlbumsByUserId(id);
        return ResponseEntity.ok(resultListPhotosPerUser);
    }

    @PostMapping("/album/user/permissions")
    public ResponseEntity<Object> registerAlbumShareWithUserPermissions(
            @RequestBody AlbumUserPermissionRequestBody requestBody
            ){
        PermissionDTO responseBody = null;
        try{
            AlbumDTO albumDTO = albumService.create(requestBody.getAlbum());
            UserDTO userDTO = userService.create(requestBody.getUser());

            AlbumUserKeyDTO albumUserKeyId = restControllerUtil
                    .buildAlbumUserKey(userDTO.getId(), albumDTO.getId());

            PermissionDTO permissionDTO = restControllerUtil
                    .buildPermission(albumUserKeyId, albumDTO, userDTO,
                            requestBody.isReading(), requestBody.isWriting());

            responseBody = permissionService.create(permissionDTO);

        }catch(Exception e){
            return ResponseEntity.internalServerError().body(responseMessage);

        }

        return ResponseEntity.ok(responseBody);

    }

    @PutMapping("/permissions/change")
    public ResponseEntity<Object> changePermissionsUserByAlbum(
            @RequestBody ChangePermissionsRequestBody changePermissionsRequestBody){
        PermissionDTO responseBody = null;
        try{
            AlbumUserKeyDTO albumUserKeyDTO = restControllerUtil
                    .buildAlbumUserKey(changePermissionsRequestBody.getUserId(),
                            changePermissionsRequestBody.getAlbumId());

            PermissionDTO permissionDTO = restControllerUtil.buildPermission(
                    albumUserKeyDTO, null, null,
                    changePermissionsRequestBody.isReading(),
                    changePermissionsRequestBody.isWriting());

            responseBody = permissionService.update(permissionDTO);
        }catch(Exception e){
            return ResponseEntity.internalServerError().body(responseMessage);
        }

        return ResponseEntity.ok(responseBody);
    }

    @GetMapping("/users/permission/album")
    public ResponseEntity<Object> getUsersPermissionSpecificByAlbum(
            @RequestBody UsersPermissionSpecificByAlbumRequestBody requestBody){
        List<UserDTO> users = null;
        try{
            List<Integer> usersId = permissionService.getUsersByAlbumPermission(
                    requestBody.getPermission(), requestBody.getAlbumId());

            users = restControllerUtil.getUsers(usersId);
        }catch(Exception e){
            return ResponseEntity.internalServerError().body(responseMessage);
        }

        return ResponseEntity.ok(users);
    }

    @GetMapping("/albums/system")
    public ResponseEntity<Object> getAlbumsBySystem(){
        List<AlbumDTO> albumDTOS = null;
        try{
            albumDTOS = albumService.getAll();
        }catch(Exception e){
            return ResponseEntity.internalServerError().body(responseMessage);
        }

        return ResponseEntity.ok(albumDTOS);
    }

    @GetMapping("/comment")
    public ResponseEntity<Object> getCommentPerNameOrPerUser(
            @RequestBody CommentPerNameOrPerUserRequestBody requestBody) throws IOException {
        CommentDTO commentDTO = null;
        commentDTO = restClientUtil.getCommentIf(requestBody.getName(), requestBody.getUser());
        return ResponseEntity.ok(commentDTO);
    }

}
