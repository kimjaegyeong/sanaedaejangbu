package com.e201.global.event;

import java.util.UUID;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import com.e201.api.service.store.MenuService;
import com.e201.domain.annotation.JtaTransactional;
import com.e201.domain.entity.payment.PaymentMonthlySum;
import com.e201.domain.entity.store.Menu;
import com.e201.domain.entity.store.Sales;
import com.e201.domain.repository.payment.PaymentMonthlySumRepository;
import com.e201.domain.repository.store.SalesRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PaymentEventListener {

	private final SalesRepository salesRepository;
	private final MenuService menuService;
	private final PaymentMonthlySumRepository paymentMonthlySumRepository;

	@Async
	@JtaTransactional
	@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
	public void handleSalesCreated(SalesCreatedEvent event) {
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

	@Async
	@JtaTransactional
	@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
	public void handlePaymentMonthlySumUpdate(PaymentMonthlySumEvent event) {
		paymentMonthlySumRepository
			.findTopByContractIdOrderByCreatedAtDesc(event.contractId())
			.ifPresent(sum -> sum.addAmount(event.amount()));
	}
}
