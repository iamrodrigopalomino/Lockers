package com.rodri.lockers.backendlockersproject.Repository;

import com.rodri.lockers.backendlockersproject.entity.Mail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MailRepository extends JpaRepository<Mail, UUID> {
}
