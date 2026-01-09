package com.rodri.lockers.backendlockersproject.service;

import com.rodri.lockers.backendlockersproject.Repository.AssignmentRepository;
import com.rodri.lockers.backendlockersproject.dto.AssignmentCreateDTO;
import com.rodri.lockers.backendlockersproject.dto.AssignmentResponseDTO;
import com.rodri.lockers.backendlockersproject.entity.Assignment;
import com.rodri.lockers.backendlockersproject.entity.AssignmentStatus;
import com.rodri.lockers.backendlockersproject.mapper.AssignmentMapper;
import com.rodri.lockers.backendlockersproject.service.LockerService;
import com.rodri.lockers.backendlockersproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class AssignmentService {

  private final AssignmentRepository repository;
  private final AssignmentMapper mapper;
  private final UserService userService;
  private final LockerService lockerService;

  public AssignmentResponseDTO assign(AssignmentCreateDTO dto) {
    Assignment assignment = new Assignment();
    assignment.setUser(userService.getEntity(dto.getUserId()));
    assignment.setLocker(lockerService.getEntity(dto.getLockerId()));
    assignment.setStatus(AssignmentStatus.ACTIVE);

    assignment.getLocker().setAssignedTo(assignment.getUser());

    return mapper.toResponse(repository.save(assignment));
  }

  public void unassign(UUID id) {
    Assignment assignment = repository.findById(id)
      .orElseThrow(() -> new RuntimeException("Assignment not found"));

    assignment.setStatus(AssignmentStatus.ENDED);
    assignment.setUnassignedAt(LocalDateTime.now());
    assignment.getLocker().setAssignedTo(null);

    repository.save(assignment);
  }

  @Transactional(readOnly = true)
  public List<AssignmentResponseDTO> getAll() {
    return repository.findAll()
      .stream()
      .map(mapper::toResponse)
      .toList();
  }

  @Transactional(readOnly = true)
  public AssignmentResponseDTO getById(UUID id) {
    return mapper.toResponse(repository.findById(id)
      .orElseThrow(() -> new RuntimeException("Assignment not found")));
  }
}
