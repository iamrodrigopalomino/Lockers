package com.rodri.lockers.backendlockersproject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter

@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = "password")
@Entity
@Table (name = "users")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @EqualsAndHashCode.Include
  private UUID id;
  @Column (nullable = false, length = 100)
  private String name;
  @Column (nullable = false, length = 100)
  private String apellidoPaterno;
  @Column (length = 100)
  private String apellidoMaterno;
  @Column (unique = true, nullable = false, length = 50)
  private String username;
  @Column (nullable = false, length = 60)
  @JsonIgnore
  private String password;
  private String carrera;
  @Column (nullable = false, unique = true, length = 20)
  private String boleta;
  @Enumerated (EnumType.STRING)
  @Column (nullable = false)
  private Role role;
  @Column (unique = true, nullable = false, length = 150)
  private String email;
  @Column (nullable = false, updatable = false)
  @CreationTimestamp
  private LocalDateTime createdAt;
}




