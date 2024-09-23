package com.e201.api.controller.company;

import static com.e201.global.security.auth.constant.AuthConstant.*;
import static org.springframework.http.HttpStatus.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.e201.api.controller.company.request.company.CompanyAuthRequest;
import com.e201.api.controller.company.request.employee.EmployeeAuthRequest;
import com.e201.api.controller.company.request.manager.ManagerAuthRequest;
import com.e201.api.service.company.CompanyService;
import com.e201.api.service.company.EmployeeService;
import com.e201.api.service.company.ManagerService;
import com.e201.global.security.auth.dto.AuthInfo;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CompanyAuthController {

	private final CompanyService companyService;
	private final ManagerService managerService;
	private final EmployeeService employeeService;

	@PostMapping("/companies/auth")
	public ResponseEntity<Void> authCompany(@RequestBody CompanyAuthRequest request, HttpServletRequest httpRequest) {
		AuthInfo authInfo = companyService.checkPassword(request);
		httpRequest.getSession().setAttribute(AUTH_INFO.name(), authInfo);
		return ResponseEntity.status(CREATED).build();
	}

	@PostMapping("/companies/managers/auth")
	public ResponseEntity<Void> authManager(@RequestBody ManagerAuthRequest request, HttpServletRequest httpRequest) {
		AuthInfo authInfo = managerService.checkPassword(request);
		httpRequest.getSession().setAttribute(AUTH_INFO.name(), authInfo);
		return ResponseEntity.status(CREATED).build();
	}

	@PostMapping("/companies/employees/auth")
	public ResponseEntity<Void> authEmployee(@RequestBody EmployeeAuthRequest request, HttpServletRequest httpRequest) {
		AuthInfo authInfo = employeeService.checkPassword(request);
		httpRequest.getSession().setAttribute(AUTH_INFO.name(), authInfo);
		return ResponseEntity.status(CREATED).build();
	}
}