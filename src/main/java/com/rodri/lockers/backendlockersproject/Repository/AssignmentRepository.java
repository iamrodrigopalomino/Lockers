package com.rodri.lockers.backendlockersproject.Repository;

import com.rodri.lockers.backendlockersproject.entity.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface AssignmentRepository extends JpaRepository<Assignment, UUID> {
  void deleteByLockerId(UUID lockerId);

  @Modifying
  @Query("delete from Assignment a where a.locker.id = :lockerId")
  void deleteAllByLockerId(@Param("lockerId") UUID lockerId);
}
