package com.rodri.lockers.backendlockersproject.Repository;

import com.rodri.lockers.backendlockersproject.entity.LockerRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface LockerRequestRepository extends JpaRepository<LockerRequest, UUID> {
  void deleteByLockerId(UUID lockerId);

  @Modifying
  @Query("delete from LockerRequest r where r.locker.id = :lockerId")
  void deleteAllByLockerId(@Param("lockerId") UUID lockerId);
}
