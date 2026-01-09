package com.rodri.lockers.backendlockersproject.controller;

import com.rodri.lockers.backendlockersproject.dto.LockerCreateDTO;
import com.rodri.lockers.backendlockersproject.dto.LockerEditDTO;
import com.rodri.lockers.backendlockersproject.dto.LockerResponseDTO;
import com.rodri.lockers.backendlockersproject.dto.LockerUpdateDTO;
import com.rodri.lockers.backendlockersproject.service.LockerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/lockers")
@RequiredArgsConstructor
public class LockerController {

  private final LockerService lockerService;

  @PostMapping
  public ResponseEntity<LockerResponseDTO> create(
    @Valid @RequestBody LockerCreateDTO dto) {
    return ResponseEntity.ok(lockerService.create(dto));
  }

  @GetMapping
  public ResponseEntity<List<LockerResponseDTO>> getAll() {
    return ResponseEntity.ok(lockerService.getAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<LockerResponseDTO> getById(@PathVariable UUID id) {
    return ResponseEntity.ok(lockerService.getById(id));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable UUID id) {
    lockerService.delete(id);
    return ResponseEntity.noContent().build();
  }

  @PutMapping("/{id}")
  public ResponseEntity<LockerResponseDTO> update(
    @PathVariable UUID id,
    @Valid @RequestBody LockerUpdateDTO dto) {
    return ResponseEntity.ok(lockerService.update(id, dto));
  }

  @PatchMapping("/{id}/edit")
  public ResponseEntity<LockerResponseDTO> edit(
    @PathVariable UUID id,
    @Valid @RequestBody LockerEditDTO dto) {
    return ResponseEntity.ok(lockerService.edit(id, dto));
  }
}
