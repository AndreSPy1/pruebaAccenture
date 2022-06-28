package com.accenture.pruebaAccenture.service;

import com.accenture.pruebaAccenture.dto.AlbumDTO;
import com.accenture.pruebaAccenture.model.Album;
import com.accenture.pruebaAccenture.payload.ResponseMessage;
import com.accenture.pruebaAccenture.repository.AlbumRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumServiceImpl implements IAlbumService{
    @Autowired
    private AlbumRepository albumRepository;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private ResponseMessage message;

    @Override
    public AlbumDTO create(AlbumDTO object) {
        AlbumDTO albumDTO = null;
        try{
            Album album = mapper.map(object, Album.class);
            albumDTO = mapper.map(albumRepository.save(album), AlbumDTO.class);
        }catch(Exception e){
            message.setMessage("Error al Guardar Album");
        }
        return albumDTO;
    }

    @Override
    public AlbumDTO get(int id) {
        AlbumDTO albumDTO = null;
        try{
            Album album = albumRepository.getReferenceById(id);
            albumDTO = mapper.map(album, AlbumDTO.class);
        }catch(Exception e){
            message.setMessage("Error al Obtener Album");
        }
        return albumDTO;
    }

    @Override
    public AlbumDTO update(AlbumDTO object) {
        AlbumDTO albumDTO = null;
        try{
            if(!albumRepository.existsById(object.getId())){
                message.setMessage("No existe el album");
                throw new Exception();
            }
            try{
                Album album = mapper.map(object, Album.class);
                album = albumRepository.save(album);
                albumDTO = mapper.map(album, AlbumDTO.class);
            }catch(Exception e){
                message.setMessage("Error al actualizar album");
            }
        }catch(Exception e){
            message.setMessage("Error al Obtener album, "+message.getMessage());
        }

        return albumDTO;
    }

    @Override
    public void delete(int id) {
        try{
            albumRepository.deleteById(id);
        }catch(Exception e){
            message.setMessage("Error al borrar album");
        }
    }

    @Override
    public List<AlbumDTO> getAll() {
        List<AlbumDTO> albumDTOS = null;
        try{
            List<Album> album = albumRepository.findAll();
            albumDTOS = mapper.map(album, List.class);
        }catch(Exception e){
            message.setMessage("Error al encontrar albums");
        }
        return albumDTOS;
    }
}
