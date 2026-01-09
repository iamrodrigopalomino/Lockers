package com.rodri.lockers.backendlockersproject.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MailCreateDTO {
  @NotBlank
  private String from;
  @NotBlank
  private String to;
  @NotBlank
  private String subject;
  @NotBlank
  private String body;
}
