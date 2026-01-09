package com.rodri.lockers.backendlockersproject.Repository;

import com.rodri.lockers.backendlockersproject.entity.Locker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LockerRepository extends JpaRepository<Locker, UUID> {
}
