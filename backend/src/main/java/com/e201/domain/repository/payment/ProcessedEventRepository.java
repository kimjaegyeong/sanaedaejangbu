package com.e201.domain.repository.payment;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.e201.domain.entity.outbox.ProcessedEvent;

public interface ProcessedEventRepository extends JpaRepository<ProcessedEvent, UUID> {

	boolean existsByEventIdAndConsumerType(UUID eventId, String consumerType);
}
