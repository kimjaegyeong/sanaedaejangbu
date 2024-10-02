package com.e201.api.controller.payment;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import com.e201.api.controller.payment.request.EmployeePaymentCondition;
import com.e201.api.controller.payment.request.EmployeeTotalPaymentCondition;
import com.e201.api.controller.payment.response.EmployeePaymentResponse;
import com.e201.api.controller.payment.response.EmployeeTotalPaymentResponse;
import com.e201.api.service.payment.PaymentService;
import com.e201.global.security.auth.dto.AuthInfo;
import com.e201.global.security.auth.resolver.Auth;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PaymentController {

	private final PaymentService paymentService;

	@GetMapping("/payments/companies/employees")
	public ResponseEntity<Page<EmployeeTotalPaymentResponse>> findEmployeePaymentPage(@Auth AuthInfo authInfo,
		@ModelAttribute EmployeeTotalPaymentCondition condition, Pageable pageable) {
		UUID companyId = authInfo.getId();
		Page<EmployeeTotalPaymentResponse> response = paymentService.findEmployeeTotalPayments(companyId, condition,
			pageable);
		return ResponseEntity.ok(response);
	}

	@GetMapping("/payments/companies/employees/{id}")
	public ResponseEntity<EmployeePaymentResponse> findEmployeePayment(
		@ModelAttribute EmployeePaymentCondition condition, Pageable pageable) {
		EmployeePaymentResponse response = paymentService.findEmployeePayments(condition, pageable);
		return ResponseEntity.ok(response);
	}
}