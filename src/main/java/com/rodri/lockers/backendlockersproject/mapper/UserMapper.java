package com.rodri.lockers.backendlockersproject.mapper;

import com.rodri.lockers.backendlockersproject.dto.UserCreateDTO;
import com.rodri.lockers.backendlockersproject.dto.UserResponseDTO;
import com.rodri.lockers.backendlockersproject.dto.UserUpdateDTO;
import com.rodri.lockers.backendlockersproject.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

  public User toEntity(UserCreateDTO dto) {
    User user = new User();
    user.setName(dto.getName());
    user.setApellidoPaterno(dto.getApellidoPaterno());
    user.setApellidoMaterno(dto.getApellidoMaterno());
    user.setUsername(dto.getUsername());
    user.setEmail(dto.getEmail());
    user.setBoleta(dto.getBoleta());
    user.setCarrera(dto.getCarrera());
    return user;
  }

  public void updateEntity(UserUpdateDTO dto, User user) {
    if (dto.getName() != null) user.setName(dto.getName());
    if (dto.getApellidoPaterno() != null) user.setApellidoPaterno(dto.getApellidoPaterno());
    if (dto.getApellidoMaterno() != null) user.setApellidoMaterno(dto.getApellidoMaterno());
    if (dto.getEmail() != null) user.setEmail(dto.getEmail());
    if (dto.getCarrera() != null) user.setCarrera(dto.getCarrera());
  }

  public UserResponseDTO toResponse(User user) {
    UserResponseDTO dto = new UserResponseDTO();
    dto.setId(user.getId());
    dto.setName(user.getName());
    dto.setApellidoPaterno(user.getApellidoPaterno());
    dto.setApellidoMaterno(user.getApellidoMaterno());
    dto.setUsername(user.getUsername());
    dto.setEmail(user.getEmail());
    dto.setBoleta(user.getBoleta());
    dto.setCarrera(user.getCarrera());
    dto.setRole(user.getRole());
    dto.setCreatedAt(user.getCreatedAt());
    return dto;
  }
}
