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

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class OutboxScheduler {

	private final OutboxRepository outboxRepository;
	private final RabbitTemplate rabbitTemplate;

	@Scheduled(fixedDelay = 5000)
	@JtaTransactional
	public void publishPendingEvents() {
		List<Outbox> pendingEvents = outboxRepository.findByStatus(OutboxStatus.PENDING);
		pendingEvents.forEach(outbox -> {
			rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE, outbox.getEventType(), outbox.getPayload());
			outbox.markAsPublished();
		});
	}
}
