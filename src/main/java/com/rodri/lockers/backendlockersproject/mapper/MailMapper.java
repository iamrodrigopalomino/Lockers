package com.rodri.lockers.backendlockersproject.mapper;

import com.rodri.lockers.backendlockersproject.dto.MailCreateDTO;
import com.rodri.lockers.backendlockersproject.dto.MailResponseDTO;
import com.rodri.lockers.backendlockersproject.entity.Mail;
import org.springframework.stereotype.Component;

@Component
public class MailMapper {

  public Mail toEntity(MailCreateDTO dto) {
    Mail mail = new Mail();
    mail.setFrom(dto.getFrom());
    mail.setTo(dto.getTo());
    mail.setSubject(dto.getSubject());
    mail.setBody(dto.getBody());
    return mail;
  }

  public MailResponseDTO toResponse(Mail mail) {
    MailResponseDTO dto = new MailResponseDTO();
    dto.setId(mail.getId());
    dto.setFrom(mail.getFrom());
    dto.setTo(mail.getTo());
    dto.setSubject(mail.getSubject());
    dto.setBody(mail.getBody());
    dto.setStatus(mail.getStatus());
    dto.setErrorMessage(mail.getErrorMessage());
    dto.setCreatedAt(mail.getCreatedAt());
    dto.setSentAt(mail.getSentAt());
    return dto;
  }
}
