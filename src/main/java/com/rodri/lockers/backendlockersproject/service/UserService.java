package com.rodri.lockers.backendlockersproject.service;

import com.rodri.lockers.backendlockersproject.Repository.UserRepository;
import com.rodri.lockers.backendlockersproject.dto.UserCreateDTO;
import com.rodri.lockers.backendlockersproject.dto.UserLoginDTO;
import com.rodri.lockers.backendlockersproject.dto.UserResponseDTO;
import com.rodri.lockers.backendlockersproject.dto.UserUpdateDTO;
import com.rodri.lockers.backendlockersproject.entity.Role;
import com.rodri.lockers.backendlockersproject.entity.User;
import com.rodri.lockers.backendlockersproject.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final UserMapper userMapper;
  private final PasswordEncoder passwordEncoder;

  public UserResponseDTO create(UserCreateDTO dto) {
    User user = userMapper.toEntity(dto);
    user.setPassword(passwordEncoder.encode(dto.getPassword()));
    user.setRole(Role.STUDENT);
    return userMapper.toResponse(userRepository.save(user));
  }

  public UserResponseDTO update(UUID id, UserUpdateDTO dto) {
    User user = getEntity(id);
    userMapper.updateEntity(dto, user);
    return userMapper.toResponse(userRepository.save(user));
  }

  @Transactional(readOnly = true)
  public List<UserResponseDTO> getAll() {
    return userRepository.findAll()
      .stream()
      .map(userMapper::toResponse)
      .toList();
  }

  @Transactional(readOnly = true)
  public UserResponseDTO getById(UUID id) {
    return userMapper.toResponse(getEntity(id));
  }

  public User getEntity(UUID id) {
    return userRepository.findById(id)
      .orElseThrow(() -> new RuntimeException("User not found"));
  }

  public Optional<UserResponseDTO> authenticate(UserLoginDTO dto) {
    return userRepository.findByUsername(dto.getUsername())
      .filter(user -> passwordEncoder.matches(dto.getPassword(), user.getPassword()))
      .map(userMapper::toResponse);
  }
}
