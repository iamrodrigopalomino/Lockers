package com.rodri.lockers.backendlockersproject.mapper;

import com.rodri.lockers.backendlockersproject.dto.AssignmentResponseDTO;
import com.rodri.lockers.backendlockersproject.entity.Assignment;
import org.springframework.stereotype.Component;

@Component
public class AssignmentMapper {

  public AssignmentResponseDTO toResponse(Assignment assignment) {
    AssignmentResponseDTO dto = new AssignmentResponseDTO();
    dto.setId(assignment.getId());
    dto.setUserId(assignment.getUser().getId());
    dto.setLockerId(assignment.getLocker().getId());
    dto.setAssignedAt(assignment.getAssignedAt());
    dto.setUnassignedAt(assignment.getUnassignedAt());
    dto.setStatus(assignment.getStatus());
    return dto;
  }
}
