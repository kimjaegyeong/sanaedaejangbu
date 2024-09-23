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
import com.e201.global.security.auth.dto.AuthInfo;
import com.e201.global.security.auth.resolver.Auth;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ContractController {

	private final ContractService contractService;

	@PostMapping("/contracts")
	public ResponseEntity<ContractCreateResponse> create(@Auth AuthInfo authInfo, @RequestBody ContractCreateRequest request) {
		ContractCreateResponse response = contractService.create(authInfo.getRoleType(), request);
		return ResponseEntity.status(CREATED).body(response);
	}

	@PostMapping("/contracts/respond")
	public ResponseEntity<ContractRespondResponse> respond(@Auth AuthInfo authInfo, @RequestBody ContractRespondCondition request) {
		ContractRespondResponse response = contractService.respond(authInfo.getRoleType(), request);
		return ResponseEntity.status(OK).body(response);
	}

	@DeleteMapping("/contracts/{contractId}")
	public ResponseEntity<Object> delete(@PathVariable String contractId) {
<<<<<<< HEAD
<<<<<<< HEAD
		contractService.delete(contractId);
		return ResponseEntity.status(NO_CONTENT).build();
=======
		try{
			contractService.delete(contractId);
			return ResponseEntity.status(NO_CONTENT).build();
		} catch (Exception e){
			return ResponseEntity.status(BAD_REQUEST).build();
		}
>>>>>>> b57a788 ([#17] feat: 계약 생성, 수락, 삭제 기능 구현)
=======
		contractService.delete(contractId);
		return ResponseEntity.status(NO_CONTENT).build();
>>>>>>> b32c375 ([#17] refactor: ContractController 예외처리 수정)
	}
}
