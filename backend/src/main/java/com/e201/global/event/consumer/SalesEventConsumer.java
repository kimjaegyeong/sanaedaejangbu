package com.e201.global.event.consumer;

import java.util.UUID;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.e201.api.service.store.MenuService;
import com.e201.domain.annotation.JtaTransactional;
import com.e201.domain.entity.store.Menu;
import com.e201.domain.entity.store.Sales;
import com.e201.domain.repository.store.SalesRepository;
import com.e201.global.config.RabbitMQConfig;
import com.e201.global.event.SalesCreatedEvent;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SalesEventConsumer {

	private final ObjectMapper objectMapper;
	private final SalesRepository salesRepository;
	private final MenuService menuService;

	@RabbitListener(queues = RabbitMQConfig.SALES_QUEUE)
	@JtaTransactional
	public void handleSalesCreated(String payload) throws Exception {
		SalesCreatedEvent event = objectMapper.readValue(payload, SalesCreatedEvent.class);
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
	}
}
