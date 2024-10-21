package com.e201.global.event.consumer;

import java.util.UUID;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.e201.api.service.store.MenuService;
import com.e201.domain.annotation.JtaTransactional;
import com.e201.domain.entity.outbox.ProcessedEvent;
import com.e201.domain.entity.store.Menu;
import com.e201.domain.entity.store.Sales;
import com.e201.domain.repository.payment.ProcessedEventRepository;
import com.e201.domain.repository.store.SalesRepository;
import com.e201.global.config.RabbitMQConfig;
import com.e201.global.event.OutboxMessage;
import com.e201.global.event.SalesCreatedEvent;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SalesEventConsumer {

	private static final String CONSUMER_TYPE = "SALES";

	private final ObjectMapper objectMapper;
	private final SalesRepository salesRepository;
	private final MenuService menuService;
	private final ProcessedEventRepository processedEventRepository;

	@RabbitListener(queues = RabbitMQConfig.SALES_QUEUE)
	@JtaTransactional
	public void handleSalesCreated(String message) throws Exception {
		OutboxMessage outboxMessage = objectMapper.readValue(message, OutboxMessage.class);

		if (processedEventRepository.existsByEventIdAndConsumerType(outboxMessage.eventId(), CONSUMER_TYPE)) {
			return;
		}

		SalesCreatedEvent event = objectMapper.readValue(outboxMessage.payload(), SalesCreatedEvent.class);
		for (UUID menuId : event.menuIds()) {
			Menu menu = menuService.findEntity(menuId);
			Sales sales = Sales.builder()
				.companyId(event.companyId())
				.menu(menu)
				.employeeId(event.employeeId())
				.paymentId(event.paymentId())
				.storeId(event.storeId())
				.build();
			salesRepository.save(sales);
		}

		processedEventRepository.save(ProcessedEvent.builder()
			.eventId(outboxMessage.eventId())
			.consumerType(CONSUMER_TYPE)
			.build());
	}
}
