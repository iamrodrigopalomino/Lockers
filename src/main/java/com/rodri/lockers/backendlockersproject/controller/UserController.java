package com.rodri.lockers.backendlockersproject.controller;

import com.rodri.lockers.backendlockersproject.dto.UserCreateDTO;
import com.rodri.lockers.backendlockersproject.dto.UserLoginDTO;
import com.rodri.lockers.backendlockersproject.dto.UserResponseDTO;
import com.rodri.lockers.backendlockersproject.dto.UserUpdateDTO;
import com.rodri.lockers.backendlockersproject.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @PostMapping
  public ResponseEntity<UserResponseDTO> create(
    @Valid @RequestBody UserCreateDTO dto) {
    return ResponseEntity.ok(userService.create(dto));
  }

  @GetMapping
  public ResponseEntity<List<UserResponseDTO>> getAll() {
    return ResponseEntity.ok(userService.getAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserResponseDTO> getById(@PathVariable UUID id) {
    return ResponseEntity.ok(userService.getById(id));
  }

  @PutMapping("/{id}")
  public ResponseEntity<UserResponseDTO> update(
    @PathVariable UUID id,
    @Valid @RequestBody UserUpdateDTO dto) {
    return ResponseEntity.ok(userService.update(id, dto));
  }

  @PostMapping("/login")
  public ResponseEntity<UserResponseDTO> login(
    @Valid @RequestBody UserLoginDTO dto) {
    return userService.authenticate(dto)
      .map(ResponseEntity::ok)
      .orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
  }
}
