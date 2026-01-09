package com.rodri.lockers.backendlockersproject.service;

import com.rodri.lockers.backendlockersproject.Repository.LockerRequestRepository;
import com.rodri.lockers.backendlockersproject.Repository.MailRepository;
import com.rodri.lockers.backendlockersproject.dto.MailCreateDTO;
import com.rodri.lockers.backendlockersproject.dto.MailRequestNotifyDTO;
import com.rodri.lockers.backendlockersproject.dto.MailResponseDTO;
import com.rodri.lockers.backendlockersproject.entity.Mail;
import com.rodri.lockers.backendlockersproject.entity.MailStatus;
import com.rodri.lockers.backendlockersproject.entity.LockerRequest;
import com.rodri.lockers.backendlockersproject.mapper.MailMapper;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MailService {

  private final MailRepository mailRepository;
  private final MailMapper mailMapper;
  private final LockerRequestRepository lockerRequestRepository;
  private final JavaMailSender mailSender;

  @Value("${app.mail.from-address:${spring.mail.username}}")
  private String fromAddress;

  @Value("${app.mail.from-name:LockersApp}")
  private String fromName;

  public MailResponseDTO create(MailCreateDTO dto) {
    Mail mail = mailMapper.toEntity(dto);
    mail.setStatus(MailStatus.PENDIENTE);
    mail.setSentAt(LocalDateTime.now());
    return mailMapper.toResponse(mailRepository.save(mail));
  }

  public void markAsSent(Mail mail) {
    mail.setStatus(MailStatus.ENVIADO);
    mail.setSentAt(LocalDateTime.now());
    mailRepository.save(mail);
  }

  public void markAsFailed(Mail mail, String error) {
    mail.setStatus(MailStatus.ERROR);
    mail.setErrorMessage(error);
    mailRepository.save(mail);
  }

  @Transactional(readOnly = true)
  public List<MailResponseDTO> getAll() {
    return mailRepository.findAll()
      .stream()
      .map(mailMapper::toResponse)
      .toList();
  }

  @Transactional(readOnly = true)
  public MailResponseDTO getById(UUID id) {
    return mailMapper.toResponse(mailRepository.findById(id)
      .orElseThrow(() -> new RuntimeException("Mail not found")));
  }

  @Transactional
  public MailResponseDTO sendLockerRequestNotification(UUID requestId, MailRequestNotifyDTO dto) {
    LockerRequest request = lockerRequestRepository.findById(requestId)
      .orElseThrow(() -> new RuntimeException("Request not found"));

    if (request.getUser() == null || request.getUser().getEmail() == null) {
      throw new RuntimeException("User email not found");
    }

    String subject = (dto.getSubject() == null || dto.getSubject().isBlank())
      ? "Actualización de solicitud de locker"
      : dto.getSubject().trim();
    String body = dto.getBody();

    Mail mail = new Mail();
    mail.setFrom(fromAddress);
    mail.setTo(request.getUser().getEmail());
    mail.setSubject(subject);
    mail.setBody(body);
    mail.setStatus(MailStatus.PENDIENTE);

    try {
      sendEmail(mail.getTo(), subject, body);
      mail.setStatus(MailStatus.ENVIADO);
      mail.setSentAt(LocalDateTime.now());
    } catch (MessagingException | UnsupportedEncodingException ex) {
      mail.setStatus(MailStatus.ERROR);
      mail.setErrorMessage(ex.getMessage());
      throw new RuntimeException("No se pudo enviar el correo");
    }

    return mailMapper.toResponse(mail);
  }

  private void sendEmail(String to, String subject, String body)
    throws MessagingException, UnsupportedEncodingException {
    MimeMessage message = mailSender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(message, "UTF-8");
    helper.setTo(to);
    helper.setSubject(subject);
    helper.setText(body, false);
    helper.setFrom(fromAddress, fromName);
    mailSender.send(message);
  }

}
