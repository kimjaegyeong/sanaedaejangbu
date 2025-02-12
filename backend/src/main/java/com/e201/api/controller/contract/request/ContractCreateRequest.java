package com.e201.api.controller.contract.request;

import java.util.UUID;

import com.e201.domain.entity.contract.Contract;
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
import com.e201.domain.entity.contract.ContractStatus;
=======
import com.e201.domain.entity.contract.Status;
>>>>>>> 6b9cc73 ([#17] feat: 계약 생성, 수락, 삭제 기능 구현)
=======
import com.e201.domain.entity.contract.Status;
>>>>>>> b57a788 ([#17] feat: 계약 생성, 수락, 삭제 기능 구현)
=======
import com.e201.domain.entity.contract.ContractStatus;
>>>>>>> 81f23e0 ([#17] feat: soft Delete 관련 BaseEntity Method 추가)
=======
import com.e201.domain.entity.contract.ContractStatus;
>>>>>>> 0de46e05944cf4306bb967ec34570e374df4dd85

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ContractCreateRequest {

	@NotBlank
	// @Size(min = 36)
	private String senderId;

	@NotBlank
<<<<<<< HEAD
<<<<<<< HEAD
	private String storeId;
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
	private int settlementDate;
=======
=======
	private String receiverRegisterNumber;
>>>>>>> e31cce2 ([#25] refactor: OCR관련 로직 변경에 따른 코드 수정)

	@NotBlank
	private int settlementDay;
>>>>>>> 32ca6e1 ([#17] refactor: 변수명, 함수 순서 일부 수정, Entity 삭제 메소드 명 변경)
=======
	private String receiverRegisterNumber;

	@NotBlank
	private int settlementDay;
>>>>>>> 0de46e05944cf4306bb967ec34570e374df4dd85

	@Builder
	private ContractCreateRequest(String senderId, String receiverRegisterNumber, int settlementDay) {
		this.senderId = senderId;
		this.receiverRegisterNumber = receiverRegisterNumber;
		this.settlementDay = settlementDay;
	}

	public Contract toEntity(UUID companyId, UUID storeId, ContractStatus status) {
		return Contract.builder()
<<<<<<< HEAD
<<<<<<< HEAD
			.companyId(UUID.fromString(companyId))
			.storeId(UUID.fromString(storeId))
<<<<<<< HEAD
			.settlementDate(settlementDate)
=======
=======
>>>>>>> b57a788 ([#17] feat: 계약 생성, 수락, 삭제 기능 구현)
	private int sattlementDate;
=======
	private int settlementDate;
>>>>>>> 81f23e0 ([#17] feat: soft Delete 관련 BaseEntity Method 추가)

	@Builder
	public ContractCreateRequest(String companyId, String storeId, int settlementDate) {
		this.companyId = companyId;
		this.storeId = storeId;
		this.settlementDate = settlementDate;
	}

	public Contract toEntity(ContractStatus status){
		return Contract.builder()
			.companyId(UUID.fromString(companyId))
			.storeId(UUID.fromString(storeId))
<<<<<<< HEAD
			.sattlementDate(sattlementDate)
<<<<<<< HEAD
>>>>>>> 6b9cc73 ([#17] feat: 계약 생성, 수락, 삭제 기능 구현)
=======
>>>>>>> b57a788 ([#17] feat: 계약 생성, 수락, 삭제 기능 구현)
=======
			.settlementDate(settlementDate)
>>>>>>> 81f23e0 ([#17] feat: soft Delete 관련 BaseEntity Method 추가)
=======
=======
			.companyId(companyId)
			.storeId(storeId)
>>>>>>> 2190cdc ([#63] fix: contract 생성/조회 시 UUID 수신 불가)
			.settlementDay(settlementDay)
>>>>>>> 32ca6e1 ([#17] refactor: 변수명, 함수 순서 일부 수정, Entity 삭제 메소드 명 변경)
=======
			.companyId(companyId)
			.storeId(storeId)
			.settlementDay(settlementDay)
>>>>>>> 0de46e05944cf4306bb967ec34570e374df4dd85
			.status(status)
			.build();
	}
}
