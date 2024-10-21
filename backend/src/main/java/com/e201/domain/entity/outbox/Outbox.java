package com.e201.domain.entity.outbox;

import java.util.UUID;

import com.e201.domain.entity.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
public class Outbox extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "outbox_id", columnDefinition = "BINARY(16)")
	private UUID id;

	@Column(name = "event_type", nullable = false)
	private String eventType;

	@Column(name = "payload", nullable = false, columnDefinition = "TEXT")
	private String payload;

	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable = false)
	private OutboxStatus status;

	@Builder
	private Outbox(String eventType, String payload) {
		this.eventType = eventType;
		this.payload = payload;
		this.status = OutboxStatus.PENDING;
	}

	public void markAsPublished() {
		this.status = OutboxStatus.PUBLISHED;
	}
}
