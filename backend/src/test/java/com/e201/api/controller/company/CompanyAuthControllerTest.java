package com.e201.api.controller.company;

import static com.e201.global.security.auth.constant.RoleType.*;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.e201.api.controller.company.request.company.CompanyAuthRequest;
import com.e201.api.controller.company.request.employee.EmployeeAuthRequest;
import com.e201.api.controller.company.request.manager.ManagerAuthRequest;
import com.e201.api.service.company.CompanyService;
import com.e201.api.service.company.EmployeeService;
import com.e201.api.service.company.ManagerService;
import com.e201.global.security.auth.dto.AuthInfo;
import com.e201.restdocs.AbstractRestDocsTest;

@WebMvcTest(CompanyAuthController.class)
class CompanyAuthControllerTest extends AbstractRestDocsTest {

	@MockBean
	CompanyService companyService;

	@MockBean
	ManagerService managerService;

	@MockBean
	EmployeeService employeeService;

	@DisplayName("기업 계정으로 로그인한다.")
	@Test
	void company_auth_success() throws Exception {
		// given
		UUID companyId = UUID.randomUUID();
		CompanyAuthRequest request = createCompanyAuthRequest("company@test.com", "12341234");
		String requestJson = objectMapper.writeValueAsString(request);
		AuthInfo response = new AuthInfo(companyId, COMPANY);

		doReturn(response).when(companyService).checkPassword(any());

		// when //then
		mockMvc.perform(post("/companies/auth")
				.contentType(APPLICATION_JSON)
				.content(requestJson)
			)
			.andExpect(status().isCreated());
	}

	@DisplayName("관리자 계정으로 로그인한다.")
	@Test
	void manager_auth_success() throws Exception {
		// given
		UUID managerId = UUID.randomUUID();
		ManagerAuthRequest request = createManagerAuthRequest("관리자코드", "12341234");
		String requestJson = objectMapper.writeValueAsString(request);
		AuthInfo response = new AuthInfo(managerId, MANAGER);

		doReturn(response).when(managerService).checkPassword(any());

		// when //then
		mockMvc.perform(post("/companies/managers/auth")
				.contentType(APPLICATION_JSON)
				.content(requestJson)
			)
			.andExpect(status().isCreated());
	}

	@DisplayName("직원 계정으로 로그인한다.")
	@Test
	void employee_auth_success() throws Exception {
		// given
		UUID employee = UUID.randomUUID();
		EmployeeAuthRequest request = createEmployeeAuthRequest("직원코드", "12341234");
		String requestJson = objectMapper.writeValueAsString(request);
		AuthInfo response = new AuthInfo(employee, EMPLOYEE);

		doReturn(response).when(employeeService).checkPassword(any());

		// when //then
		mockMvc.perform(post("/companies/employees/auth")
				.contentType(APPLICATION_JSON)
				.content(requestJson)
			)
			.andExpect(status().isCreated());
	}

	private CompanyAuthRequest createCompanyAuthRequest(String email, String password) {
		return CompanyAuthRequest.builder()
			.email(email)
			.password(password)
			.build();
	}

	private ManagerAuthRequest createManagerAuthRequest(String code, String password) {
		return ManagerAuthRequest.builder()
			.code(code)
			.password(password)
			.build();
	}

	private EmployeeAuthRequest createEmployeeAuthRequest(String code, String password) {
		return EmployeeAuthRequest.builder()
			.code(code)
			.password(password)
			.build();
	}
}