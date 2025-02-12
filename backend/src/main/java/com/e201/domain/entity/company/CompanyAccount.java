package com.e201.domain.entity.company;

import java.util.UUID;

import com.e201.domain.entity.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CompanyAccount extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "account_id", columnDefinition = "BINARY(16)")
	private UUID id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "company_id")
	private Company company;

	@Column(name = "account_number")
	private String number;

	@Column(name = "bank_code")
	private String bankCode;

	@Column(name = "bank_name")
	private String bankName;

	@Builder
	private CompanyAccount(UUID id, Company company, String number, String bankCode, String bankName) {
		this.id = id;
		this.company = company;
		this.number = number;
		this.bankCode = bankCode;
		this.bankName = bankName;
	}
}
