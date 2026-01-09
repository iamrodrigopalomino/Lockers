package com.rodri.lockers.backendlockersproject.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = "body")
@Entity
@Table (name = "mails")
public class Mail {
  @Id
  @GeneratedValue (strategy = GenerationType.UUID)
  @EqualsAndHashCode.Include
  private UUID id;
  @Column(name = "mail_from", nullable = false, length = 150)
  private String from;
  @Column(name = "mail_to", nullable = false, length = 150)
  private String to;
  @Column(nullable = false, length = 255)
  private String subject;
  @Column(nullable = false, columnDefinition = "TEXT")
  private String body;
  @Enumerated (EnumType.STRING)
  @Column (nullable = false)
  private MailStatus status;
  @Column(length = 500)
  private String errorMessage;
  @Column(nullable = false, updatable = false)
  @CreationTimestamp
  private LocalDateTime createdAt;
  @Column(updatable = false)
  private LocalDateTime sentAt;
}
