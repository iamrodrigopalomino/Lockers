package com.rodri.lockers.backendlockersproject.dto;

import com.rodri.lockers.backendlockersproject.entity.Role;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class UserResponseDTO {
  private UUID id;
  private String name;
  private String apellidoPaterno;
  private  String apellidoMaterno;
  private String username;
  private String email;
  private String carrera;
  private String boleta;
  private Role role;
  private LocalDateTime createdAt;
}
