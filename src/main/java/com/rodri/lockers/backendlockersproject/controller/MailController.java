package com.rodri.lockers.backendlockersproject.controller;

import com.rodri.lockers.backendlockersproject.dto.MailCreateDTO;
import com.rodri.lockers.backendlockersproject.dto.MailRequestNotifyDTO;
import com.rodri.lockers.backendlockersproject.dto.MailResponseDTO;
import com.rodri.lockers.backendlockersproject.service.MailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/mails")
@RequiredArgsConstructor
public class MailController {

  private final MailService mailService;

  @PostMapping
  public ResponseEntity<MailResponseDTO> create(
    @Valid @RequestBody MailCreateDTO dto) {
    return ResponseEntity.ok(mailService.create(dto));
  }

  @GetMapping
  public ResponseEntity<List<MailResponseDTO>> getAll() {
    return ResponseEntity.ok(mailService.getAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<MailResponseDTO> getById(@PathVariable UUID id) {
    return ResponseEntity.ok(mailService.getById(id));
  }

  @PostMapping("/locker-requests/{requestId}/notify")
  public ResponseEntity<MailResponseDTO> notifyLockerRequest(
    @PathVariable UUID requestId,
    @Valid @RequestBody MailRequestNotifyDTO dto) {
    return ResponseEntity.ok(mailService.sendLockerRequestNotification(requestId, dto));
  }
}
