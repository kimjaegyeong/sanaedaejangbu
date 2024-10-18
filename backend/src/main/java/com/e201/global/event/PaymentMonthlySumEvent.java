package com.e201.global.event;

import java.util.UUID;

public record PaymentMonthlySumEvent(
	UUID contractId,
	Long amount
) {}
