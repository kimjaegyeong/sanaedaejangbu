package com.e201.domain.repository.payment;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.e201.domain.entity.outbox.Outbox;
import com.e201.domain.entity.outbox.OutboxStatus;

public interface OutboxRepository extends JpaRepository<Outbox, UUID> {

	List<Outbox> findByStatus(OutboxStatus status);
}
