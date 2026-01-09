package com.rodri.lockers.backendlockersproject.mapper;

import com.rodri.lockers.backendlockersproject.dto.LockerCreateDTO;
import com.rodri.lockers.backendlockersproject.dto.LockerEditDTO;
import com.rodri.lockers.backendlockersproject.dto.LockerResponseDTO;
import com.rodri.lockers.backendlockersproject.dto.LockerUpdateDTO;
import com.rodri.lockers.backendlockersproject.entity.Locker;
import org.springframework.stereotype.Component;

@Component
public class LockerMapper {

  public Locker toEntity(LockerCreateDTO dto) {
    Locker locker = new Locker();
    locker.setNumero(dto.getNumero());
    locker.setEdificio(dto.getEdificio());
    locker.setPiso(dto.getPiso());
    locker.setTipo(dto.getTipo());
    return locker;
  }

  public void updateEntity(LockerUpdateDTO dto, Locker locker) {
    if (dto.getNumero() != null) {
      locker.setNumero(dto.getNumero());
    }
    if (dto.getEdificio() != null) {
      locker.setEdificio(dto.getEdificio());
    }
    if (dto.getPiso() != null) {
      locker.setPiso(dto.getPiso());
    }
    if (dto.getTipo() != null) {
      locker.setTipo(dto.getTipo());
    }
    if (dto.getStatus() != null) {
      locker.setStatus(dto.getStatus());
    }
  }

  public void updateEntity(LockerEditDTO dto, Locker locker) {
    if (dto.getNumero() != null) {
      locker.setNumero(dto.getNumero());
    }
    if (dto.getEdificio() != null) {
      locker.setEdificio(dto.getEdificio());
    }
    if (dto.getPiso() != null) {
      locker.setPiso(dto.getPiso());
    }
    if (dto.getTipo() != null) {
      locker.setTipo(dto.getTipo());
    }
  }

  public LockerResponseDTO toResponse(Locker locker) {
    LockerResponseDTO dto = new LockerResponseDTO();
    dto.setId(locker.getId());
    dto.setNumero(locker.getNumero());
    dto.setEdificio(locker.getEdificio());
    dto.setPiso(locker.getPiso());
    dto.setTipo(locker.getTipo());
    dto.setStatus(locker.getStatus());

    if (locker.getAssignedTo() != null) {
      dto.setAssignedUserId(locker.getAssignedTo().getId());
    }

    return dto;
  }
}
