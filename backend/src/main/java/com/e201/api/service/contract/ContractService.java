package com.e201.api.service.contract;

<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
=======
import java.util.ArrayList;
>>>>>>> 31cf432 ([#40] feat: Contract 조회 기능 구현)
=======
>>>>>>> 54ad0bd ([#40] feat: 계약 조회 기능 구현)
=======
>>>>>>> 0de46e05944cf4306bb967ec34570e374df4dd85
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.e201.api.controller.contract.request.ContractCreateRequest;
import com.e201.api.controller.contract.request.ContractFindRequest;
import com.e201.api.controller.contract.request.ContractRespondCondition;
import com.e201.api.controller.contract.response.ContractCreateResponse;
import com.e201.api.controller.contract.response.ContractFindResponse;
import com.e201.api.controller.contract.response.ContractRespondResponse;
import com.e201.api.controller.contract.response.EmployeeFindStoreResponse;
import com.e201.api.service.company.CompanyService;
import com.e201.api.service.store.StoreService;
import com.e201.domain.annotation.JtaTransactional;
import com.e201.domain.entity.contract.Contract;
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
import com.e201.domain.entity.contract.ContractResponse;
=======
=======
import com.e201.domain.entity.contract.ContractFindCond;
import com.e201.domain.entity.contract.ContractFindStatus;
>>>>>>> df7e7ba ([#40] feat: Contract 조회 기능 구현)
=======
import com.e201.domain.entity.contract.ContractFindCond;
import com.e201.domain.entity.contract.ContractFindStatus;
>>>>>>> 31cf432 ([#40] feat: Contract 조회 기능 구현)
=======
>>>>>>> 96dbfb8 ([#85] feat: employee가 계약된 가게를 조회한다.)
=======
>>>>>>> 8560af0 ([#85] feat: employee가 계약된 가게를 조회한다.)
import com.e201.domain.entity.contract.ContractRespondType;
>>>>>>> 32ca6e1 ([#17] refactor: 변수명, 함수 순서 일부 수정, Entity 삭제 메소드 명 변경)
import com.e201.domain.entity.contract.ContractStatus;
=======
import com.e201.domain.entity.contract.Status;
>>>>>>> 6b9cc73 ([#17] feat: 계약 생성, 수락, 삭제 기능 구현)
=======
import com.e201.domain.entity.contract.Status;
>>>>>>> b57a788 ([#17] feat: 계약 생성, 수락, 삭제 기능 구현)
=======
import com.e201.domain.entity.contract.ContractResponse;
import com.e201.domain.entity.contract.ContractStatus;
>>>>>>> 81f23e0 ([#17] feat: soft Delete 관련 BaseEntity Method 추가)
=======
import com.e201.domain.entity.contract.ContractRespondType;
import com.e201.domain.entity.contract.ContractStatus;
>>>>>>> 0de46e05944cf4306bb967ec34570e374df4dd85
import com.e201.domain.repository.contract.ContractRepository;
import com.e201.global.security.auth.constant.RoleType;
import com.e201.global.security.auth.dto.AuthInfo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@JtaTransactional(readOnly = true)
public class ContractService {

	private final ContractRepository contractRepository;
	private final CompanyService companyService;
	private final StoreService storeService;

	@JtaTransactional
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 6b9cc73 ([#17] feat: 계약 생성, 수락, 삭제 기능 구현)
=======
>>>>>>> b57a788 ([#17] feat: 계약 생성, 수락, 삭제 기능 구현)
	public ContractCreateResponse create(String senderType, ContractCreateRequest request){
		Contract contract;
		switch (senderType){
			case "STORE":
				contract = request.toEntity(Status.COMPANY_WAITING);
				break;
			case "COMPANY":
				contract = request.toEntity(Status.STORE_WAITING);
				break;
			default:
				throw new RuntimeException("unknown sender type");
		}

<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
=======
	public ContractCreateResponse create(RoleType senderType, ContractCreateRequest request){
=======
	public ContractCreateResponse create(RoleType senderType, ContractCreateRequest request) {
>>>>>>> b4d6ecc ([#17] feat: auth 인증 관련 내용 controller에 적용)
		Contract contract = createContractBySenderType(senderType, request);
>>>>>>> 81f23e0 ([#17] feat: soft Delete 관련 BaseEntity Method 추가)
=======
	public ContractCreateResponse create(RoleType senderType, ContractCreateRequest request){
		Contract contract = createContractBySenderType(senderType, request);
>>>>>>> 81f23e0 ([#17] feat: soft Delete 관련 BaseEntity Method 추가)
=======
	public ContractCreateResponse create(AuthInfo authInfo, ContractCreateRequest request) {
		Contract contract = createContractBySenderType(authInfo, request);
<<<<<<< HEAD
<<<<<<< HEAD
>>>>>>> e31cce2 ([#25] refactor: OCR관련 로직 변경에 따른 코드 수정)
=======
		System.out.println("PASSED");
>>>>>>> 2190cdc ([#63] fix: contract 생성/조회 시 UUID 수신 불가)
=======
>>>>>>> f64057c ([#96] refactor: 정산 조회 관련 DTO 수정)
		Contract savedContract = contractRepository.save(contract);
		return new ContractCreateResponse(savedContract.getId());
=======
=======
>>>>>>> 6b9cc73 ([#17] feat: 계약 생성, 수락, 삭제 기능 구현)
=======
>>>>>>> b57a788 ([#17] feat: 계약 생성, 수락, 삭제 기능 구현)
		try{
			Contract savedContract = contractRepository.save(contract);
			return new ContractCreateResponse(savedContract.getId());
		} catch (Exception e){
			throw new RuntimeException("failed to create contract");
		}
<<<<<<< HEAD
<<<<<<< HEAD
>>>>>>> b57a788 ([#17] feat: 계약 생성, 수락, 삭제 기능 구현)
=======
		Contract savedContract = contractRepository.save(contract);
		return new ContractCreateResponse(savedContract.getId());
>>>>>>> b32c375 ([#17] refactor: ContractController 예외처리 수정)
=======
	public ContractCreateResponse create(AuthInfo authInfo, ContractCreateRequest request) {
		Contract contract = createContractBySenderType(authInfo, request);
		Contract savedContract = contractRepository.save(contract);
		return new ContractCreateResponse(savedContract.getId());
>>>>>>> 0de46e05944cf4306bb967ec34570e374df4dd85
	}

	private Contract createContractBySenderType(AuthInfo authInfo, ContractCreateRequest request) {
		UUID companyId;
		UUID storeId;
		ContractStatus status;
		switch (authInfo.getRoleType()) {
			case STORE -> {
				storeId = authInfo.getId();
				companyId = companyService.findCompanyByRegisterNo(request.getReceiverRegisterNumber()).getId();
				status = ContractStatus.STORE_REQUEST;
			}
			case COMPANY -> {
				companyId = authInfo.getId();
				storeId = storeService.findStoreIdByRegisterNo(request.getReceiverRegisterNumber()).getId();
				status = ContractStatus.COMPANY_REQUEST;
			}
			default -> throw new IllegalArgumentException("Unknown sender type: " + authInfo.getRoleType());
		}

		return request.toEntity(companyId, storeId, status);
	}

	public Contract findEntity(UUID id) {
		return contractRepository.findById(id)
			.orElseThrow(() -> new RuntimeException("not found exception"));
	}

<<<<<<< HEAD
<<<<<<< HEAD
	public UUID findContractId(UUID companyId, UUID storeId) {
<<<<<<< HEAD
<<<<<<< HEAD
		Contract contract = contractRepository.findContractByCompanyIdAndStoreIdAndDeleteYN(companyId, storeId, "N")
=======
		Contract contract = contractRepository.findContractByCompanyIdAndStoreIdAndDeleteYN(companyId, storeId,"N")
>>>>>>> 31cf432 ([#40] feat: Contract 조회 기능 구현)
=======
		Contract contract = contractRepository.findContractByCompanyIdAndStoreIdAndDeleteYN(companyId, storeId, "N")
>>>>>>> 54ad0bd ([#40] feat: 계약 조회 기능 구현)
			.orElseThrow(() -> new RuntimeException("not found exception"));
		return contract.getId();
=======
	public Contract findContractWithCompanyIdAndStoreId(UUID companyId, UUID storeId) {
		Contract contract = contractRepository.findContractWithCompanyIdAndStoreId(storeId, companyId).getFirst();
		return contract;
>>>>>>> 65a8c38 ([#58] feat: contractId 조회 기능 추가 구현)
	}

<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
	public List<ContractFindResponse> find(AuthInfo authInfo, ContractFindStatus status, ContractFindCond cond) {
		List<ContractFindResponse> response = contractRepository.findMyContracts(authInfo, status, cond, null, 10);
=======
	public List<ContractFindResponse> find(AuthInfo authInfo, ContractFindStatus status, ContractFindCond cond){
		List<ContractFindResponse> response = contractRepository.findMyContracts(authInfo, status, cond,null, 10);
>>>>>>> 31cf432 ([#40] feat: Contract 조회 기능 구현)
=======
	public List<ContractFindResponse> find(AuthInfo authInfo, ContractFindStatus status, ContractFindCond cond) {
		List<ContractFindResponse> response = contractRepository.findMyContracts(authInfo, status, cond, null, 10);
>>>>>>> 54ad0bd ([#40] feat: 계약 조회 기능 구현)
=======
	public Page<ContractFindResponse> find(AuthInfo authInfo, ContractFindRequest request, Pageable pageable) {
		Page<ContractFindResponse> response = contractRepository.findMyContracts(authInfo, request, pageable);
>>>>>>> eae207b ([#60] refactor: Contract 조회 로직 수정)
=======
	public Page<ContractFindResponse> find(AuthInfo authInfo, ContractFindRequest request, Pageable pageable) {
		Page<ContractFindResponse> response = contractRepository.findMyContracts(authInfo, request, pageable);
>>>>>>> dbe64c6 ([#60] refactor: Contract 조회 로직 수정)
=======
	public Page<ContractFindResponse> find(AuthInfo authInfo, ContractFindRequest request, Pageable pageable) {
		Page<ContractFindResponse> response = contractRepository.findMyContracts(authInfo, request, pageable);
>>>>>>> e104de7 ([#60] refactor: Contract 조회 로직 수정)
=======
	public List<ContractFindResponse> find(AuthInfo authInfo, ContractFindRequest request) {
		List<ContractFindResponse> response = contractRepository.findMyContracts(authInfo, request);
>>>>>>> 14fa6f8 ([#78] refactor: 계약 조회 기능 수정)
		return response;
	}

	public List<EmployeeFindStoreResponse> findStores(AuthInfo authInfo) {
		List<EmployeeFindStoreResponse> response = contractRepository.findStores(authInfo);
=======
	public Contract findContractWithCompanyIdAndStoreId(UUID companyId, UUID storeId) {
		Contract contract = contractRepository.findContractWithCompanyIdAndStoreId(storeId, companyId).getFirst();
		return contract;
	}

	public List<ContractFindResponse> find(AuthInfo authInfo, ContractFindRequest request) {
		List<ContractFindResponse> response = contractRepository.findMyContracts(authInfo, request);
>>>>>>> 0de46e05944cf4306bb967ec34570e374df4dd85
		return response;
	}

	public List<EmployeeFindStoreResponse> findStores(AuthInfo authInfo) {
		List<EmployeeFindStoreResponse> response = contractRepository.findStores(authInfo);
		return response;
	}

	@JtaTransactional
	public ContractRespondResponse respond(RoleType senderType, ContractRespondCondition request) {
		Contract contract = contractRepository.findById(UUID.fromString(request.getContractId()))
			.orElseThrow(() -> new RuntimeException("not found exception"));

		ContractStatus status = updateContractStatus(contract, request.getRespondResult());
		contract.update(status);
		return new ContractRespondResponse(contract.getId());
	}

	private ContractStatus updateContractStatus(Contract contract, ContractRespondType response) {
		return switch (response) {
			case APPROVE -> ContractStatus.COMPLETE;
			case REJECT -> contract.getStatus() == ContractStatus.COMPANY_REQUEST ? ContractStatus.STORE_REJECT :
				ContractStatus.COMPANY_REJECT;
		};
<<<<<<< HEAD
=======
=======
		Contract savedContract = contractRepository.save(contract);
		return new ContractCreateResponse(savedContract.getId());
>>>>>>> d4d2cc4 ([#17] refactor: ContractController 예외처리 수정)
=======
>>>>>>> b57a788 ([#17] feat: 계약 생성, 수락, 삭제 기능 구현)
=======
		Contract savedContract = contractRepository.save(contract);
		return new ContractCreateResponse(savedContract.getId());
>>>>>>> b32c375 ([#17] refactor: ContractController 예외처리 수정)
	}

	private Contract createContractBySenderType(RoleType senderType, ContractCreateRequest request){
		return switch (senderType) {
			case STORE -> request.toEntity(ContractStatus.STORE_REQUEST);
			case COMPANY -> request.toEntity(ContractStatus.COMPANY_REQUEST);
			default -> throw new IllegalArgumentException("Unknown sender type: " + senderType);
		};
	}

	@JtaTransactional
<<<<<<< HEAD
	public ContractRespondResponse respond(RoleType senderType, ContractRespondCondition request){
		Contract contract = contractRepository.findById(UUID.fromString(request.getContractId()))
=======
	public void delete(String contractId) {
		Contract contract = contractRepository.findByIdAndDeleteYN(UUID.fromString(contractId), "N")
>>>>>>> 54ad0bd ([#40] feat: 계약 조회 기능 구현)
			.orElseThrow(() -> new RuntimeException("not found exception"));

		ContractStatus status = updateContractStatus(contract, request.getRespondResult());
		contract.update(status);
		return new ContractRespondResponse(contract.getId());
<<<<<<< HEAD
>>>>>>> 6b9cc73 ([#17] feat: 계약 생성, 수락, 삭제 기능 구현)
=======
>>>>>>> b57a788 ([#17] feat: 계약 생성, 수락, 삭제 기능 구현)
	}

	private ContractStatus updateContractStatus(Contract contract, ContractResponse response){
		return switch(response){
			case APPROVE -> ContractStatus.COMPLETE;
			case REJECT -> contract.getStatus() == ContractStatus.COMPANY_REQUEST ? ContractStatus.STORE_REJECT : ContractStatus.COMPANY_REJECT;
			default -> throw new IllegalArgumentException("unknown respond result");
		};
	}

	@JtaTransactional
<<<<<<< HEAD
	public void delete(String contractId){
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 81f23e0 ([#17] feat: soft Delete 관련 BaseEntity Method 추가)
=======
	public void delete(String contractId) {
<<<<<<< HEAD
>>>>>>> b4d6ecc ([#17] feat: auth 인증 관련 내용 controller에 적용)
		Contract contract = contractRepository.findById(UUID.fromString(contractId))
=======
		Contract contract = contractRepository.findByIdAndDeleteYN(UUID.fromString(contractId), "N")
>>>>>>> 13366f8 ([#40] feat: 계약 조회 기능 구현)
			.orElseThrow(() -> new RuntimeException("not found exception"));

<<<<<<< HEAD
		contract.delete();
<<<<<<< HEAD
=======
		contractRepository.deleteById(UUID.fromString(contractId));
>>>>>>> 6b9cc73 ([#17] feat: 계약 생성, 수락, 삭제 기능 구현)
=======
		contractRepository.deleteById(UUID.fromString(contractId));
>>>>>>> b57a788 ([#17] feat: 계약 생성, 수락, 삭제 기능 구현)
=======
>>>>>>> 81f23e0 ([#17] feat: soft Delete 관련 BaseEntity Method 추가)
=======
		contract.softDelete();
>>>>>>> 32ca6e1 ([#17] refactor: 변수명, 함수 순서 일부 수정, Entity 삭제 메소드 명 변경)
=======
	}

	@JtaTransactional
	public void delete(String contractId) {
		Contract contract = contractRepository.findByIdAndDeleteYN(UUID.fromString(contractId), "N")
			.orElseThrow(() -> new RuntimeException("not found exception"));

		contract.softDelete();
>>>>>>> 0de46e05944cf4306bb967ec34570e374df4dd85
	}
}
