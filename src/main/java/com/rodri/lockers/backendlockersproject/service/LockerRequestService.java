package com.rodri.lockers.backendlockersproject.service;

import com.rodri.lockers.backendlockersproject.Repository.LockerRequestRepository;
import com.rodri.lockers.backendlockersproject.dto.LockerRequestCreateDTO;
import com.rodri.lockers.backendlockersproject.dto.LockerRequestResponseDTO;
import com.rodri.lockers.backendlockersproject.dto.LockerRequestUpdateDTO;
import com.rodri.lockers.backendlockersproject.entity.LockerRequest;
import com.rodri.lockers.backendlockersproject.entity.RequestStatus;
import com.rodri.lockers.backendlockersproject.mapper.LockerRequestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LockerRequestService {

  private final LockerRequestRepository repository;
  private final LockerRequestMapper mapper;
  private final UserService userService;
  private final LockerService lockerService;

  public LockerRequestResponseDTO create(UUID userId, LockerRequestCreateDTO dto) {
    LockerRequest request = new LockerRequest();
    request.setUser(userService.getEntity(userId));
    request.setLocker(lockerService.getEntity(dto.getLockerId()));
    request.setStatus(RequestStatus.PENDIENTE);
    return mapper.toResponse(repository.save(request));
  }

  @Transactional
  public LockerRequestResponseDTO update(UUID id, LockerRequestUpdateDTO dto) {
    LockerRequest request = repository.findById(id)
      .orElseThrow(() -> new RuntimeException("Request not found"));

    request.setStatus(dto.getStatus());
    if (dto.getStatus() == RequestStatus.APROBADO) {
      request.setApprovedDate(LocalDateTime.now());
      request.setRejectionReason(null);
      request.getLocker().setAssignedTo(request.getUser());
      request.getLocker().setStatus(com.rodri.lockers.backendlockersproject.entity.LockerStatus.ASIGNADO);
    } else if (dto.getStatus() == RequestStatus.RECHAZADO) {
      request.setApprovedDate(null);
      request.setRejectionReason(dto.getRejectionReason());
      request.getLocker().setAssignedTo(null);
      request.getLocker().setStatus(com.rodri.lockers.backendlockersproject.entity.LockerStatus.DISPONIBLE);
    } else {
      request.setApprovedDate(null);
      request.setRejectionReason(null);
      request.getLocker().setAssignedTo(null);
      request.getLocker().setStatus(com.rodri.lockers.backendlockersproject.entity.LockerStatus.DISPONIBLE);
    }

    return mapper.toResponse(repository.save(request));
  }

  @Transactional(readOnly = true)
  public List<LockerRequestResponseDTO> getAll() {
    return repository.findAll()
      .stream()
      .map(mapper::toResponse)
      .toList();
  }

  @Transactional(readOnly = true)
  public LockerRequestResponseDTO getById(UUID id) {
    return mapper.toResponse(repository.findById(id)
      .orElseThrow(() -> new RuntimeException("Request not found")));
  }
}
