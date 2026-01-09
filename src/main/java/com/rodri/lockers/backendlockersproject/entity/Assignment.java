package com.rodri.lockers.backendlockersproject.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = {"user", "locker"})
@Entity
@Table(name = "assignments")
public class Assignment {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @EqualsAndHashCode.Include
  private UUID id;
  @ManyToOne (fetch = FetchType.LAZY)
  @JoinColumn (name = "user_id", nullable = false)
  private User user;
  @ManyToOne (fetch = FetchType.LAZY)
  @JoinColumn (name = "locker_id", nullable = false)
  private Locker locker;
  @Column(nullable = false, updatable = false)
  @CreationTimestamp
  private LocalDateTime assignedAt;
  private LocalDateTime unassignedAt;
  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private AssignmentStatus status;
}
