package com.e201.api.controller.store;

import static com.e201.global.security.auth.constant.AuthConstant.*;
import static org.springframework.http.HttpStatus.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.e201.api.controller.store.request.StoreAuthRequest;
import com.e201.api.controller.store.response.StoreAuthResponse;
import com.e201.api.service.store.StoreService;
import com.e201.domain.entity.store.Store;
import com.e201.global.security.auth.dto.AuthInfo;
import com.e201.global.security.auth.resolver.Auth;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class StoreAuthController {

	private final StoreService storeService;
	@PostMapping("/stores/auth")
	public ResponseEntity<StoreAuthResponse> login(@RequestBody StoreAuthRequest request, HttpServletRequest httpRequest){
		AuthInfo authInfo = storeService.checkPassword(request);
		StoreAuthResponse storeAuthResponse = storeService.login(request);

		httpRequest.getSession().setAttribute(AUTH_INFO.name(), authInfo);
		
		return ResponseEntity.status(CREATED).body(storeAuthResponse);
	}

	@GetMapping("/stores/auth/check")
	public ResponseEntity<StoreAuthResponse> loginCheck(@Auth AuthInfo authInfo){
		StoreAuthResponse storeAuthResponse = storeService.checkLogin(authInfo.getId(), authInfo.getRoleType());
		return ResponseEntity.status(OK).body(storeAuthResponse);
	}

	@DeleteMapping("/stores/auth")
	public ResponseEntity<Void> logout(HttpServletRequest httpRequest){
		httpRequest.getSession().invalidate();
		return ResponseEntity.status(NO_CONTENT).build();
	}
}
