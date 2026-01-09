package com.rodri.lockers.backendlockersproject.dto;

import com.rodri.lockers.backendlockersproject.entity.LockerSize;
import com.rodri.lockers.backendlockersproject.entity.LockerStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class LockerResponseDTO {

  private UUID id;
  private String numero;
  private String edificio;
  private String piso;
  private LockerSize tipo;
  private LockerStatus status;

  // NUEVO
  private UUID assignedUserId;
}
