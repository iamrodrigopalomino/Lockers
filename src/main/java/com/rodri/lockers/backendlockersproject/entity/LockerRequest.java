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
@ToString(exclude = {"user", "locker"})
@Entity
@Table(name = "locker_requests")
public class LockerRequest {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @EqualsAndHashCode.Include
  private UUID id;
  @ManyToOne (fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;
  @ManyToOne (fetch = FetchType.LAZY)
  @JoinColumn(name = "locker_id", nullable = false)
  private Locker locker;
  @Column(nullable = false)
  @CreationTimestamp
  private LocalDateTime requestDate;
  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private RequestStatus status;
  private LocalDateTime approvedDate;
  @Column(length = 255)
  private String rejectionReason;
}
