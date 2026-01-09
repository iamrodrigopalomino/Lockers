package com.rodri.lockers.backendlockersproject.mapper;

import com.rodri.lockers.backendlockersproject.dto.LockerRequestResponseDTO;
import com.rodri.lockers.backendlockersproject.entity.LockerRequest;
import org.springframework.stereotype.Component;

@Component
public class LockerRequestMapper {

  public LockerRequestResponseDTO toResponse(LockerRequest request) {
    LockerRequestResponseDTO dto = new LockerRequestResponseDTO();
    dto.setId(request.getId());
    dto.setUserId(request.getUser().getId());
    dto.setLockerId(request.getLocker().getId());
    dto.setRequestDate(request.getRequestDate());
    dto.setApprovedDate(request.getApprovedDate());
    dto.setRejectionReason(request.getRejectionReason());
    dto.setStatus(request.getStatus());
    return dto;
  }
}
