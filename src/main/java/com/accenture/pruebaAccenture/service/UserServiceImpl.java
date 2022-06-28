package com.accenture.pruebaAccenture.service;

import com.accenture.pruebaAccenture.dto.UserDTO;
import com.accenture.pruebaAccenture.model.User;
import com.accenture.pruebaAccenture.payload.ResponseMessage;
import com.accenture.pruebaAccenture.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private ResponseMessage message;

    @Override
    public UserDTO create(UserDTO object) {
        UserDTO userDTO = null;
        try{
            User user = mapper.map(object, User.class);
            userDTO = mapper.map(userRepository.save(user), UserDTO.class);
        }catch(Exception e){
            message.setMessage("Error al Guardar User");
        }
        return userDTO;
    }

    @Override
    public UserDTO get(int id) {
        UserDTO userDTO = null;
        try{
            User user = userRepository.getReferenceById(id);
            userDTO = mapper.map(user, UserDTO.class);
        }catch(Exception e){
            message.setMessage("Error al Obtener User");
        }
        return userDTO;
    }

    @Override
    public UserDTO update(UserDTO object) {
        UserDTO userDTO = null;
        try{
            if(!userRepository.existsById(object.getId())){
                message.setMessage("No existe el user");
                throw new Exception();
            }
            try{
                User user = mapper.map(object, User.class);
                user = userRepository.save(user);
                userDTO = mapper.map(user, UserDTO.class);
            }catch(Exception e){
                message.setMessage("Error al actualizar user");
            }
        }catch(Exception e){
            message.setMessage("Error al Obtener user, "+message.getMessage());
        }

        return userDTO;
    }

    @Override
    public void delete(int id) {
        try{
            userRepository.deleteById(id);
        }catch(Exception e){
            message.setMessage("Error al borrar user");
        }
    }
}
