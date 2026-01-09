package com.rodri.lockers.backendlockersproject.dto;

import com.rodri.lockers.backendlockersproject.entity.MailStatus;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class MailResponseDTO {
  private UUID id;
  private String from;
  private String to;
  private String subject;
  private MailStatus status;
  private LocalDateTime createdAt;
  private LocalDateTime sentAt;
  private String errorMessage;
  private String body;


}
