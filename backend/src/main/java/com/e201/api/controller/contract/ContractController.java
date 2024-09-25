package com.e201.api.controller.contract;

import static org.springframework.http.HttpStatus.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.e201.api.controller.contract.request.ContractCreateRequest;
import com.e201.api.controller.contract.request.ContractRespondCondition;
import com.e201.api.controller.contract.response.ContractCreateResponse;
import com.e201.api.controller.contract.response.ContractRespondResponse;
import com.e201.api.service.contract.ContractService;
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
import com.e201.global.security.auth.dto.AuthInfo;
import com.e201.global.security.auth.resolver.Auth;
=======
>>>>>>> 6b9cc73 ([#17] feat: 계약 생성, 수락, 삭제 기능 구현)
=======
>>>>>>> b57a788 ([#17] feat: 계약 생성, 수락, 삭제 기능 구현)
=======
import com.e201.global.security.auth.dto.AuthInfo;
import com.e201.global.security.auth.resolver.Auth;
>>>>>>> 81f23e0 ([#17] feat: soft Delete 관련 BaseEntity Method 추가)

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ContractController {

	private final ContractService contractService;

	@PostMapping("/contracts")
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
	public ResponseEntity<ContractCreateResponse> create(@Auth AuthInfo authInfo, @RequestBody ContractCreateRequest request) {
=======
	public ResponseEntity<ContractCreateResponse> create(@Auth AuthInfo authInfo,
		@RequestBody ContractCreateRequest request) {
>>>>>>> b4d6ecc ([#17] feat: auth 인증 관련 내용 controller에 적용)
		ContractCreateResponse response = contractService.create(authInfo.getRoleType(), request);
=======
=======
>>>>>>> b57a788 ([#17] feat: 계약 생성, 수락, 삭제 기능 구현)
	public ResponseEntity<ContractCreateResponse> create(@RequestBody ContractCreateRequest request) {
		// senderType
		String senderType = "STORE";
		ContractCreateResponse response = contractService.create(senderType, request);
<<<<<<< HEAD
>>>>>>> 6b9cc73 ([#17] feat: 계약 생성, 수락, 삭제 기능 구현)
=======
>>>>>>> b57a788 ([#17] feat: 계약 생성, 수락, 삭제 기능 구현)
=======
	public ResponseEntity<ContractCreateResponse> create(@Auth AuthInfo authInfo, @RequestBody ContractCreateRequest request) {
		ContractCreateResponse response = contractService.create(authInfo.getRoleType(), request);
>>>>>>> 81f23e0 ([#17] feat: soft Delete 관련 BaseEntity Method 추가)
		return ResponseEntity.status(CREATED).body(response);
	}

	@PostMapping("/contracts/respond")
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
	public ResponseEntity<ContractRespondResponse> respond(@Auth AuthInfo authInfo, @RequestBody ContractRespondCondition request) {
=======
	public ResponseEntity<ContractRespondResponse> respond(@Auth AuthInfo authInfo,
		@RequestBody ContractRespondCondition request) {
>>>>>>> b4d6ecc ([#17] feat: auth 인증 관련 내용 controller에 적용)
		ContractRespondResponse response = contractService.respond(authInfo.getRoleType(), request);
=======
	public ResponseEntity<ContractRespondResponse> respond(@RequestBody ContractRespondCondition request) {
		String senderType = "STORE";
		ContractRespondResponse response = contractService.respond(request);
>>>>>>> 6b9cc73 ([#17] feat: 계약 생성, 수락, 삭제 기능 구현)
=======
	public ResponseEntity<ContractRespondResponse> respond(@RequestBody ContractRespondCondition request) {
		String senderType = "STORE";
		ContractRespondResponse response = contractService.respond(request);
>>>>>>> b57a788 ([#17] feat: 계약 생성, 수락, 삭제 기능 구현)
=======
	public ResponseEntity<ContractRespondResponse> respond(@Auth AuthInfo authInfo, @RequestBody ContractRespondCondition request) {
		ContractRespondResponse response = contractService.respond(authInfo.getRoleType(), request);
>>>>>>> 81f23e0 ([#17] feat: soft Delete 관련 BaseEntity Method 추가)
		return ResponseEntity.status(OK).body(response);
	}

	@DeleteMapping("/contracts/{contractId}")
<<<<<<< HEAD
	public ResponseEntity<Object> delete(@PathVariable String contractId) {
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
=======
	public ResponseEntity<Object> delete(@Auth AuthInfo authInfo, @PathVariable String contractId) {
>>>>>>> b4d6ecc ([#17] feat: auth 인증 관련 내용 controller에 적용)
		contractService.delete(contractId);
		return ResponseEntity.status(NO_CONTENT).build();
=======
=======
>>>>>>> 6b9cc73 ([#17] feat: 계약 생성, 수락, 삭제 기능 구현)
=======
>>>>>>> b57a788 ([#17] feat: 계약 생성, 수락, 삭제 기능 구현)
		try{
			contractService.delete(contractId);
			return ResponseEntity.status(NO_CONTENT).build();
		} catch (Exception e){
			return ResponseEntity.status(BAD_REQUEST).build();
		}
<<<<<<< HEAD
<<<<<<< HEAD
>>>>>>> b57a788 ([#17] feat: 계약 생성, 수락, 삭제 기능 구현)
=======
		contractService.delete(contractId);
		return ResponseEntity.status(NO_CONTENT).build();
>>>>>>> b32c375 ([#17] refactor: ContractController 예외처리 수정)
=======
>>>>>>> 6b9cc73 ([#17] feat: 계약 생성, 수락, 삭제 기능 구현)
=======
		contractService.delete(contractId);
		return ResponseEntity.status(NO_CONTENT).build();
>>>>>>> d4d2cc4 ([#17] refactor: ContractController 예외처리 수정)
=======
>>>>>>> b57a788 ([#17] feat: 계약 생성, 수락, 삭제 기능 구현)
=======
		contractService.delete(contractId);
		return ResponseEntity.status(NO_CONTENT).build();
>>>>>>> b32c375 ([#17] refactor: ContractController 예외처리 수정)
	}
}
