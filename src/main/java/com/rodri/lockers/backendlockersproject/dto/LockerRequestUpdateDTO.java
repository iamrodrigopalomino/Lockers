package com.rodri.lockers.backendlockersproject.dto;

import com.rodri.lockers.backendlockersproject.entity.RequestStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LockerRequestUpdateDTO {
  @NotNull
  private RequestStatus status;
  private String rejectionReason;
}
