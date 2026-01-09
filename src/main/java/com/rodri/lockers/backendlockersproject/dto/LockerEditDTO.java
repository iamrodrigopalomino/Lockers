package com.rodri.lockers.backendlockersproject.dto;

import com.rodri.lockers.backendlockersproject.entity.LockerSize;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LockerEditDTO {
  private String numero;
  private String edificio;
  private String piso;
  private LockerSize tipo;
}
