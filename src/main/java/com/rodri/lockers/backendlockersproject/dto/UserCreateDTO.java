package com.rodri.lockers.backendlockersproject.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateDTO {
  @NotBlank
  private String name;
  @NotBlank
  private String apellidoPaterno;
  private String apellidoMaterno;
  @NotBlank
  private String username;
  @NotBlank
  private String password;
  private String carrera;
  @NotBlank
  private String boleta;
  @Email
  @NotBlank
  private String email;
}
