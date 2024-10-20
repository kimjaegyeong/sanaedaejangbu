package com.e201.global.event.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.e201.domain.annotation.JtaTransactional;
import com.e201.domain.repository.payment.PaymentMonthlySumRepository;
import com.e201.global.config.RabbitMQConfig;
import com.e201.global.event.PaymentMonthlySumEvent;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PaymentMonthlySumConsumer {

	private final ObjectMapper objectMapper;
	private final PaymentMonthlySumRepository paymentMonthlySumRepository;

	@RabbitListener(queues = RabbitMQConfig.MONTHLY_SUM_QUEUE)
	@JtaTransactional
	public void handlePaymentMonthlySumUpdate(String payload) throws Exception {
		PaymentMonthlySumEvent event = objectMapper.readValue(payload, PaymentMonthlySumEvent.class);
		paymentMonthlySumRepository
			.findTopByContractIdOrderByCreatedAtDesc(event.contractId())
			.ifPresent(sum -> sum.addAmount(event.amount()));
	}
}
