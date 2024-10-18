package com.e201.global.event;

import java.util.List;
import java.util.UUID;

public record SalesCreatedEvent(
	UUID paymentId,
	UUID companyId,
	UUID storeId,
	UUID employeeId,
	List<UUID> menuIds
) {}
