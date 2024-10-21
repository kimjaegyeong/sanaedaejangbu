package com.e201.domain.entity.outbox;

import java.util.UUID;

import com.e201.domain.entity.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProcessedEvent extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "processed_event_id", columnDefinition = "BINARY(16)")
	private UUID id;

	@Column(name = "event_id", nullable = false, unique = true, columnDefinition = "BINARY(16)")
	private UUID eventId;

	@Column(name = "consumer_type", nullable = false)
	private String consumerType;

	@Builder
	private ProcessedEvent(UUID eventId, String consumerType) {
		this.eventId = eventId;
		this.consumerType = consumerType;
	}
}
