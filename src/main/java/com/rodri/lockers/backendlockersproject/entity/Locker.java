package com.rodri.lockers.backendlockersproject.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@ToString(exclude = "assignedTo")
@Table(name = "lockers")
public class Locker {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @EqualsAndHashCode.Include
  private UUID id;
  @Column(nullable = false, unique = true, length = 25)
  private String numero;
  @Column(nullable = false, length = 25)
  private String edificio;
  @Column(nullable = false, length = 25)
  private String piso;
  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private LockerSize tipo;
  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private LockerStatus status;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "assigned_to", nullable = true)
  private User assignedTo;
}
