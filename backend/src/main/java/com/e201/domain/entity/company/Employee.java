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
public class Employee extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "employee_id", columnDefinition = "BINARY(16)")
	private UUID id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "company_id")
	private Company company;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "department_id")
	private Department department;

	@Column(name = "code")
	private String code;

	@Column(name = "password")
	private String password;

	@Column(name = "name")
	private String name;

	@Column(name = "support_amount")
	private Long supportAmount;

	@Builder
	private Employee(UUID id, Company company, Department department, String code, String password, String name, Long supportAmount) {
		this.id = id;
		this.company = company;
		this.department = department;
		this.code = code;
		this.password = password;
		this.name = name;
		this.supportAmount = supportAmount;
	}

	public void changePassword(String password) {
		this.password = password;
	}

	public void changeSupportAmount(Long supportAmount) {
		this.supportAmount = supportAmount;
	}
}
