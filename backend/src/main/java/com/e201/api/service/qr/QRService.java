package com.e201.api.service.qr;

import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.e201.api.controller.store.request.QRValidationRequest;
import com.e201.global.security.auth.constant.RoleType;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QRService {

	private static final long QR_TTL_MINUTES = 5;

	private final StringRedisTemplate stringRedisTemplate;

	public void create(RoleType roleType, QRValidationRequest qrValidationRequest) {
		validationAuth(roleType);
		stringRedisTemplate.opsForValue()
			.set(qrValidationRequest.getValidationId(), "1", QR_TTL_MINUTES, TimeUnit.MINUTES);
	}

	public boolean isExist(QRValidationRequest qrValidationRequest) {
		return Boolean.TRUE.equals(stringRedisTemplate.hasKey(qrValidationRequest.getValidationId()));
	}

	private void validationAuth(RoleType roleType) {
		if (roleType != RoleType.EMPLOYEE) {
			throw new RuntimeException("invalid role type");
		}
	}
}
