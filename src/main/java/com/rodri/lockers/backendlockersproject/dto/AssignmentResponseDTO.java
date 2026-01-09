package com.rodri.lockers.backendlockersproject.dto;

import com.rodri.lockers.backendlockersproject.entity.AssignmentStatus;
import lombok.Getter;
import lombok.Setter;
import java.util.UUID;
import java.time.LocalDateTime;
@Getter
@Setter
public class AssignmentResponseDTO {
  private UUID id;
  private UUID userId;
  private UUID lockerId;
  private LocalDateTime assignedAt;
  private LocalDateTime unassignedAt;
  private AssignmentStatus status;
}
