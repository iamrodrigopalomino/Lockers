package com.rodri.lockers.backendlockersproject.config;

import com.rodri.lockers.backendlockersproject.Repository.UserRepository;
import com.rodri.lockers.backendlockersproject.entity.Role;
import com.rodri.lockers.backendlockersproject.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminSeeder implements ApplicationRunner {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  @Value("${app.admin.username:}")
  private String adminUsername;

  @Value("${app.admin.password:}")
  private String adminPassword;

  @Value("${app.admin.email:}")
  private String adminEmail;

  @Value("${app.admin.boleta:}")
  private String adminBoleta;

  @Value("${app.admin.name:Admin}")
  private String adminName;

  @Value("${app.admin.apellido-paterno:Admin}")
  private String adminApellidoPaterno;

  @Value("${app.admin.apellido-materno:}")
  private String adminApellidoMaterno;

  @Value("${app.admin.carrera:}")
  private String adminCarrera;

  @Override
  public void run(ApplicationArguments args) {
    if (adminUsername.isBlank() || adminPassword.isBlank() || adminEmail.isBlank() || adminBoleta.isBlank()) {
      return;
    }

    if (userRepository.findByUsername(adminUsername).isPresent()) {
      return;
    }

    User admin = new User();
    admin.setName(adminName);
    admin.setApellidoPaterno(adminApellidoPaterno);
    admin.setApellidoMaterno(adminApellidoMaterno);
    admin.setUsername(adminUsername);
    admin.setPassword(passwordEncoder.encode(adminPassword));
    admin.setCarrera(adminCarrera);
    admin.setBoleta(adminBoleta);
    admin.setRole(Role.ADMIN);
    admin.setEmail(adminEmail);
    userRepository.save(admin);
  }
}
