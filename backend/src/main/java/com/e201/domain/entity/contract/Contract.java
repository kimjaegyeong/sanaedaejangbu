package com.e201.domain.entity.contract;

import java.util.UUID;

import com.e201.domain.entity.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
public class Contract extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(columnDefinition = "BINARY(16)", name = "contract_id")
	private UUID id;

	@Column(name = "company_id")
	private UUID companyId;

	@Column(name = "store_id")
	private UUID storeId;

	@Column(name = "status")
	@Enumerated(EnumType.STRING)
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
	private ContractStatus status;
=======
	private Status status;
>>>>>>> 6b9cc73 ([#17] feat: 계약 생성, 수락, 삭제 기능 구현)
=======
	private Status status;
>>>>>>> b57a788 ([#17] feat: 계약 생성, 수락, 삭제 기능 구현)
=======
	private ContractStatus status;
>>>>>>> 81f23e0 ([#17] feat: soft Delete 관련 BaseEntity Method 추가)
=======
	private ContractStatus status;
>>>>>>> 0de46e05944cf4306bb967ec34570e374df4dd85

	@Column(name = "settlement_day")
	private int settlementDay;

	@Builder
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
	public Contract(UUID id, UUID companyId, UUID storeId, ContractStatus status, int settlementDate) {
=======
	public Contract(UUID id, UUID companyId, UUID storeId, Status status, int sattlementDate) {
>>>>>>> 6b9cc73 ([#17] feat: 계약 생성, 수락, 삭제 기능 구현)
=======
	public Contract(UUID id, UUID companyId, UUID storeId, Status status, int sattlementDate) {
>>>>>>> b57a788 ([#17] feat: 계약 생성, 수락, 삭제 기능 구현)
=======
	public Contract(UUID id, UUID companyId, UUID storeId, ContractStatus status, int settlementDate) {
>>>>>>> 81f23e0 ([#17] feat: soft Delete 관련 BaseEntity Method 추가)
=======
	private Contract(UUID id, UUID companyId, UUID storeId, ContractStatus status, int settlementDay) {
>>>>>>> 32ca6e1 ([#17] refactor: 변수명, 함수 순서 일부 수정, Entity 삭제 메소드 명 변경)
=======
	private Contract(UUID id, UUID companyId, UUID storeId, ContractStatus status, int settlementDay) {
>>>>>>> 0de46e05944cf4306bb967ec34570e374df4dd85
		this.id = id;
		this.companyId = companyId;
		this.storeId = storeId;
		this.status = status;
<<<<<<< HEAD
<<<<<<< HEAD
		this.settlementDate = settlementDate;
<<<<<<< HEAD
	}

	public void update(ContractStatus contractStatus){
		this.status = contractStatus;
	}

	public void update(Status status){
		this.status = status;
=======
>>>>>>> 81f23e0 ([#17] feat: soft Delete 관련 BaseEntity Method 추가)
=======
		this.settlementDay = settlementDay;
>>>>>>> 32ca6e1 ([#17] refactor: 변수명, 함수 순서 일부 수정, Entity 삭제 메소드 명 변경)
=======
		this.settlementDay = settlementDay;
>>>>>>> 0de46e05944cf4306bb967ec34570e374df4dd85
	}

	public void update(ContractStatus contractStatus) {
		this.status = contractStatus;
	}
}
