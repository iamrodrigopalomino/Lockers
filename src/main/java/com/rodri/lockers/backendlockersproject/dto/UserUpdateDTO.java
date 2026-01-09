package com.rodri.lockers.backendlockersproject.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateDTO {
  private String name;
  private String apellidoPaterno;
  private String apellidoMaterno;
  private String carrera;
  private String email;
}
