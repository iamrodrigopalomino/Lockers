  package com.rodri.lockers.backendlockersproject.service;

import com.rodri.lockers.backendlockersproject.Repository.AssignmentRepository;
import com.rodri.lockers.backendlockersproject.Repository.LockerRepository;
import com.rodri.lockers.backendlockersproject.Repository.LockerRequestRepository;
import com.rodri.lockers.backendlockersproject.dto.LockerCreateDTO;
import com.rodri.lockers.backendlockersproject.dto.LockerEditDTO;
import com.rodri.lockers.backendlockersproject.dto.LockerResponseDTO;
import com.rodri.lockers.backendlockersproject.dto.LockerUpdateDTO;
import com.rodri.lockers.backendlockersproject.entity.Locker;
import com.rodri.lockers.backendlockersproject.entity.LockerStatus;
import com.rodri.lockers.backendlockersproject.mapper.LockerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

  @Service
  @RequiredArgsConstructor
  public class LockerService {

  private final LockerRepository lockerRepository;
  private final LockerMapper lockerMapper;
  private final AssignmentRepository assignmentRepository;
  private final LockerRequestRepository lockerRequestRepository;

    public LockerResponseDTO create(LockerCreateDTO dto) {
      Locker locker = lockerMapper.toEntity(dto);
      locker.setStatus(LockerStatus.DISPONIBLE);
      return lockerMapper.toResponse(lockerRepository.save(locker));
    }

  public LockerResponseDTO update(UUID id, LockerUpdateDTO dto) {
    Locker locker = getEntity(id);
    lockerMapper.updateEntity(dto, locker);
    return lockerMapper.toResponse(lockerRepository.save(locker));
  }

  public LockerResponseDTO edit(UUID id, LockerEditDTO dto) {
    Locker locker = getEntity(id);
    lockerMapper.updateEntity(dto, locker);
    return lockerMapper.toResponse(lockerRepository.save(locker));
  }

  @Transactional(readOnly = true)
  public List<LockerResponseDTO> getAll() {
    return lockerRepository.findAll()
      .stream()
      .map(lockerMapper::toResponse)
      .toList();
  }

  @Transactional(readOnly = true)
  public LockerResponseDTO getById(UUID id) {
    return lockerMapper.toResponse(getEntity(id));
  }

  @Transactional
  public void delete(UUID id) {
    Locker locker = getEntity(id);
    assignmentRepository.deleteAllByLockerId(id);
    lockerRequestRepository.deleteAllByLockerId(id);
    locker.setAssignedTo(null);
    lockerRepository.delete(locker);
  }

  public Locker getEntity(UUID id) {
    return lockerRepository.findById(id)
      .orElseThrow(() -> new RuntimeException("Locker not found"));
  }
}
