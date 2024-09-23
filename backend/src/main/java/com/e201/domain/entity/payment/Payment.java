package com.e201.domain.entity.payment;

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
public class Payment extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "payment_id", columnDefinition = "BINARY(16)")
	private UUID id;

	@Column(name = "contract_id")
	private UUID contractId;

	@Column(name = "employee_id")
	private UUID employeeId;

	@Column(name = "total_amount")
	private Long totalAmount;

	@Builder
	public Payment(UUID id, UUID contractId, UUID employeeId, Long totalAmount) {
		this.id = id;
		this.contractId = contractId;
		this.employeeId = employeeId;
		this.totalAmount = totalAmount;
	}
}