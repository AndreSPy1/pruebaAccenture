package com.accenture.pruebaAccenture.service;

import com.accenture.pruebaAccenture.dto.UserDTO;

public interface IUserService {
    UserDTO create(UserDTO object);
    UserDTO get(int id);
    UserDTO update(UserDTO object);
    void delete(int id);

}
