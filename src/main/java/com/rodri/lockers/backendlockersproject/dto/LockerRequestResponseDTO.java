package com.rodri.lockers.backendlockersproject.dto;

import com.rodri.lockers.backendlockersproject.entity.RequestStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;
@Getter
@Setter
public class LockerRequestResponseDTO {
  private UUID id;
  private UUID userId;
  private UUID lockerId;
  private LocalDateTime requestDate;
  private RequestStatus status;
  private LocalDateTime approvedDate;
  private String rejectionReason;
}
