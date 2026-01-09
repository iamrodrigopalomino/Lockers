package com.rodri.lockers.backendlockersproject.controller;

import com.rodri.lockers.backendlockersproject.dto.AssignmentCreateDTO;
import com.rodri.lockers.backendlockersproject.dto.AssignmentResponseDTO;
import com.rodri.lockers.backendlockersproject.service.AssignmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/assignments")
@RequiredArgsConstructor
public class AssignmentController {

  private final AssignmentService service;

  @PostMapping
  public ResponseEntity<AssignmentResponseDTO> assign(
    @Valid @RequestBody AssignmentCreateDTO dto) {
    return ResponseEntity.ok(service.assign(dto));
  }

  @GetMapping
  public ResponseEntity<List<AssignmentResponseDTO>> getAll() {
    return ResponseEntity.ok(service.getAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<AssignmentResponseDTO> getById(@PathVariable UUID id) {
    return ResponseEntity.ok(service.getById(id));
  }

  @PutMapping("/{id}/unassign")
  public ResponseEntity<Void> unassign(@PathVariable UUID id) {
    service.unassign(id);
    return ResponseEntity.noContent().build();
  }
}
