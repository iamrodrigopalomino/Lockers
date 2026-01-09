package com.rodri.lockers.backendlockersproject.controller;

import com.rodri.lockers.backendlockersproject.dto.LockerRequestCreateDTO;
import com.rodri.lockers.backendlockersproject.dto.LockerRequestResponseDTO;
import com.rodri.lockers.backendlockersproject.dto.LockerRequestUpdateDTO;
import com.rodri.lockers.backendlockersproject.service.LockerRequestService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/locker-requests")
@RequiredArgsConstructor
public class LockerRequestController {

  private final LockerRequestService service;

  @PostMapping("/user/{userId}")
  public ResponseEntity<LockerRequestResponseDTO> create(
    @PathVariable UUID userId,
    @Valid @RequestBody LockerRequestCreateDTO dto) {
    return ResponseEntity.ok(service.create(userId, dto));
  }

  @GetMapping
  public ResponseEntity<List<LockerRequestResponseDTO>> getAll() {
    return ResponseEntity.ok(service.getAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<LockerRequestResponseDTO> getById(@PathVariable UUID id) {
    return ResponseEntity.ok(service.getById(id));
  }

  @PutMapping("/{id}")
  public ResponseEntity<LockerRequestResponseDTO> update(
    @PathVariable UUID id,
    @Valid @RequestBody LockerRequestUpdateDTO dto) {
    return ResponseEntity.ok(service.update(id, dto));
  }
}
