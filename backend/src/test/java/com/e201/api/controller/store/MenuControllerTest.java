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

import com.e201.api.controller.store.request.MenuCreateRequest;
import com.e201.api.controller.store.request.MenuUpdateRequest;
import com.e201.api.controller.store.response.MenuCreateResponse;
import com.e201.api.controller.store.response.MenuUpdateResponse;
import com.e201.api.service.store.MenuService;
import com.e201.global.security.auth.constant.AuthConstant;
import com.e201.global.security.auth.constant.RoleType;
import com.e201.global.security.auth.dto.AuthInfo;
import com.e201.restdocs.AbstractRestDocsTest;

@WebMvcTest(MenuController.class)
public class MenuControllerTest extends AbstractRestDocsTest {
	@MockBean
	private MenuService menuService;

	@DisplayName("메뉴를 등록한다.")
	@Test
	void create_menu_success() throws Exception {
		//given
		UUID menuId = UUID.randomUUID();
		AuthInfo authInfo = new AuthInfo(UUID.randomUUID(), RoleType.STORE);
		MenuCreateRequest request = createMenuRequest();
		String requestJson = objectMapper.writeValueAsString(request);
		MenuCreateResponse response = new MenuCreateResponse(menuId);
		String responseJson = objectMapper.writeValueAsString(response);

		doReturn(response).when(menuService).create(any(),any(), any(MenuCreateRequest.class));

		//expected
		mockMvc.perform(post("/stores/menus")
				.contentType(APPLICATION_JSON)
				.content(requestJson)
				.sessionAttr(AuthConstant.AUTH_INFO.name(), authInfo)
			)
			.andExpect(status().isCreated())
			.andExpect(content().json(responseJson));
	}
	@DisplayName("메뉴를 수정한다.")
	@Test
	void modify_menu_success() throws Exception {
		UUID menuId = UUID.randomUUID();
		AuthInfo authInfo = new AuthInfo(UUID.randomUUID(), RoleType.STORE);
		MenuUpdateRequest request = createMenuUpdateRequest();
		String requestJson = objectMapper.writeValueAsString(request);
		MenuUpdateResponse response = new MenuUpdateResponse(menuId);
		String responseJson = objectMapper.writeValueAsString(response);

		doReturn(response).when(menuService).modify(any(),any(), any(MenuUpdateRequest.class));

		//expected
		mockMvc.perform(put("/stores/menus/"+menuId)
				.contentType(APPLICATION_JSON)
				.content(requestJson)
				.sessionAttr(AuthConstant.AUTH_INFO.name(), authInfo)
			)
			.andExpect(status().isOk())
			.andExpect(content().json(responseJson));

	}

	private MenuUpdateRequest createMenuUpdateRequest(){
		return MenuUpdateRequest.builder()
			.id(UUID.randomUUID())
			.menuName("메뉴이름")
			.price(2132)
			.build();
	}

	private MenuCreateRequest createMenuRequest() {
		return MenuCreateRequest.builder()
			.price(10000)
			.build();
	}
}