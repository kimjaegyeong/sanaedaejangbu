package com.e201.global.event.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.e201.domain.annotation.JtaTransactional;
import com.e201.domain.entity.outbox.ProcessedEvent;
import com.e201.domain.repository.payment.PaymentMonthlySumRepository;
import com.e201.domain.repository.payment.ProcessedEventRepository;
import com.e201.global.config.RabbitMQConfig;
import com.e201.global.event.OutboxMessage;
import com.e201.global.event.PaymentMonthlySumEvent;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PaymentMonthlySumConsumer {

	private static final String CONSUMER_TYPE = "MONTHLY_SUM";

	private final ObjectMapper objectMapper;
	private final PaymentMonthlySumRepository paymentMonthlySumRepository;
	private final ProcessedEventRepository processedEventRepository;

	@RabbitListener(queues = RabbitMQConfig.MONTHLY_SUM_QUEUE)
	@JtaTransactional
	public void handlePaymentMonthlySumUpdate(String message) throws Exception {
		OutboxMessage outboxMessage = objectMapper.readValue(message, OutboxMessage.class);

		if (processedEventRepository.existsByEventIdAndConsumerType(outboxMessage.eventId(), CONSUMER_TYPE)) {
			return;
		}

		PaymentMonthlySumEvent event = objectMapper.readValue(outboxMessage.payload(), PaymentMonthlySumEvent.class);
		paymentMonthlySumRepository
			.findTopByContractIdOrderByCreatedAtDesc(event.contractId())
			.ifPresent(sum -> sum.addAmount(event.amount()));

		processedEventRepository.save(ProcessedEvent.builder()
			.eventId(outboxMessage.eventId())
			.consumerType(CONSUMER_TYPE)
			.build());
	}
}
