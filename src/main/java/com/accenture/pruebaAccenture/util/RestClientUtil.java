package com.accenture.pruebaAccenture.util;

import com.accenture.pruebaAccenture.dto.AlbumDTO;
import com.accenture.pruebaAccenture.dto.CommentDTO;
import com.accenture.pruebaAccenture.dto.PhotoDTO;
import com.accenture.pruebaAccenture.dto.UserDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class RestClientUtil {
    private final String URI_USERS = "https://jsonplaceholder.typicode.com/users";
    private final String URI_PHOTOS = "https://jsonplaceholder.typicode.com/photos";
    private final String URI_ALBUMS = "https://jsonplaceholder.typicode.com/albums";
    private final String URI_COMMENTS = "https://jsonplaceholder.typicode.com/comments";
    @Autowired
    private RestTemplate restTemplate;
    private ObjectMapper mapper = new ObjectMapper();

    public List<UserDTO> getUsers() throws IOException {
        String response = restTemplate.getForObject(URI_USERS, String.class);
        return jsonArrayToObjectList(response, UserDTO.class);
    }

    public List<PhotoDTO> getPhotos() throws IOException {
        String response = restTemplate.getForObject(URI_PHOTOS, String.class);
        return jsonArrayToObjectList(response, PhotoDTO.class);
    }

    public List<AlbumDTO> getAlbums() throws IOException {
        String response = restTemplate.getForObject(URI_ALBUMS, String.class);
        return jsonArrayToObjectList(response, AlbumDTO.class);
    }

    public List<AlbumDTO> getAlbumsPerUser(int id) throws IOException {
        String response = restTemplate.getForObject(URI_USERS+"/"+id+"/albums", String.class);
        return jsonArrayToObjectList(response, AlbumDTO.class);
    }

    public List<PhotoDTO> getPhotosPerAlbums(int id) throws IOException {
        String response = restTemplate.getForObject(URI_ALBUMS+"/"+id+"/photos", String.class);
        return jsonArrayToObjectList(response, PhotoDTO.class);
    }

    public List<PhotoDTO> getPhotosPerAlbumsByUserId(int id) throws IOException {
        List<PhotoDTO> resultListPhotoDTOByAlbums = new ArrayList<>();
        List<AlbumDTO> resultAlbumsPerUser = getAlbumsPerUser(id);

        for (AlbumDTO albumDTO : resultAlbumsPerUser) {
            resultListPhotoDTOByAlbums.addAll(getPhotosPerAlbums(albumDTO.getId()));
        }
        return resultListPhotoDTOByAlbums;
    }

    public CommentDTO getCommentByName(String name) throws IOException {
        String response = restTemplate.getForObject(URI_COMMENTS+"/?name="+name, String.class);
        if(!response.equals("[]")){
            List<CommentDTO> commentDTOS = jsonArrayToObjectList(response, CommentDTO.class);
            return commentDTOS.get(0);
        }

        return null;
    }

    public CommentDTO getCommentByEmail(String email) throws IOException {
        String response = restTemplate.getForObject(URI_COMMENTS+"/?email="+email, String.class);
        if(!response.equals("[]")){
            List<CommentDTO> commentDTOS = jsonArrayToObjectList(response, CommentDTO.class);
            return commentDTOS.get(0);
        }

        return null;
    }

    public CommentDTO getCommentIf(String name, UserDTO user) throws IOException {
        CommentDTO commentDTO = null;
        if(name.length() > 0 || user != null){
            if(user != null){
                commentDTO = getCommentByEmail(user.getEmail());
                return commentDTO;
            }
            commentDTO = getCommentByName(name);
        }

        return commentDTO;
    }

    public <T> List<T> jsonArrayToObjectList(String json, Class<T> tClass) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        CollectionType listType = mapper.getTypeFactory().constructCollectionType(ArrayList.class, tClass);
        List<T> ts = mapper.readValue(json, listType);
        return ts;
    }
}
