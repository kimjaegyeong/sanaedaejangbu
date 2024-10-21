package com.e201.global.event;

import java.util.List;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.e201.domain.annotation.JtaTransactional;
import com.e201.domain.entity.outbox.Outbox;
import com.e201.domain.entity.outbox.OutboxStatus;
import com.e201.domain.repository.payment.OutboxRepository;
import com.e201.global.config.RabbitMQConfig;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class OutboxScheduler {

	private final OutboxRepository outboxRepository;
	private final RabbitTemplate rabbitTemplate;
	private final ObjectMapper objectMapper;

	@Scheduled(fixedDelay = 5000)
	@JtaTransactional
	public void publishPendingEvents() {
		List<Outbox> pendingEvents = outboxRepository.findByStatus(OutboxStatus.PENDING);
		pendingEvents.forEach(outbox -> {
			try {
				String message = objectMapper.writeValueAsString(
					new OutboxMessage(outbox.getId(), outbox.getPayload()));
				rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE, outbox.getEventType(), message);
				outbox.markAsPublished();
			} catch (JsonProcessingException e) {
				throw new RuntimeException("OutboxMessage 직렬화 실패", e);
			}
		});
	}
}
