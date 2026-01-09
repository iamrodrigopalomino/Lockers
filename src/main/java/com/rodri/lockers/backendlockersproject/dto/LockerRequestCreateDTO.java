package com.rodri.lockers.backendlockersproject.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


@Getter
@Setter
public class LockerRequestCreateDTO {
  @NotNull
  private UUID lockerId;
}
