package com.e201.api.controller.store;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.e201.api.controller.store.request.StoreAndStoreInfoCreateRequest;
import com.e201.api.controller.store.request.StoreCreateRequest;
import com.e201.api.controller.store.response.StoreCreateResponse;
import com.e201.api.controller.store.response.StoreDeleteResponse;
import com.e201.api.controller.store.response.StoreInfoFindResponse;
import com.e201.api.service.store.StoreService;
import com.e201.global.security.auth.constant.AuthConstant;
import com.e201.global.security.auth.constant.RoleType;
import com.e201.global.security.auth.dto.AuthInfo;
import com.e201.restdocs.AbstractRestDocsTest;

@WebMvcTest(StoreController.class)
public class StoreControllerTest extends AbstractRestDocsTest {
	@MockBean
	StoreService storeService;

	@DisplayName("식당을 등록한다.")
	@Test
	void create_store_success() throws Exception {
		// given
		UUID storeId = UUID.randomUUID();
		StoreAndStoreInfoCreateRequest request = createStoreAndStoreInfoCreateRequest();
		String requestJson = objectMapper.writeValueAsString(request);
		StoreCreateResponse response = new StoreCreateResponse(storeId);
		String responseJson = objectMapper.writeValueAsString(response);

		doReturn(response).when(storeService).create(any(StoreAndStoreInfoCreateRequest.class));

		// expected
		mockMvc.perform(post("/stores")
				.contentType(APPLICATION_JSON)
				.content(requestJson)
			)
			.andExpect(status().isCreated())
			.andExpect(content().json(responseJson));
	}

	@DisplayName("식당을 삭제한다.")
	@Test
	void delete_store_success() throws Exception {
		UUID storeId = UUID.randomUUID();
		StoreDeleteResponse response = new StoreDeleteResponse(storeId);
		String responseJson = objectMapper.writeValueAsString(response);
		AuthInfo authInfo = new AuthInfo(storeId, RoleType.STORE);
		doReturn(response).when(storeService).delete(any(), any());

		mockMvc.perform(delete("/stores")
				.contentType(APPLICATION_JSON)
				.sessionAttr(AuthConstant.AUTH_INFO.name(), authInfo)
			)
			.andExpect(status().isNoContent())
			.andExpect(content().json(responseJson));
	}
	private StoreCreateRequest createStoreRequest(UUID id) {
		return StoreCreateRequest.builder()
			.storeInfoId(id)
			.email("이메일")
			.password("비밀번호")
			.build();
	}

	private StoreAndStoreInfoCreateRequest createStoreAndStoreInfoCreateRequest() {
		return StoreAndStoreInfoCreateRequest.builder()
			.email("이메일")
			.password("비밀번호")
			.passwordConfirm("비밀번호")
			.phone("핸드폰번호")
			.businessName("사업장이름")
			.repName("대표이름")
			.address("주소")
			.registerNumber("사업자등록번호")
			.businessType("비즈니스타입")
			.openDate("개업일")
			.build();
	}

}
