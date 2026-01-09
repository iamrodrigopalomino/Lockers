package com.rodri.lockers.backendlockersproject.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MailRequestNotifyDTO {
  private String subject;
  @NotBlank
  private String body;
}
