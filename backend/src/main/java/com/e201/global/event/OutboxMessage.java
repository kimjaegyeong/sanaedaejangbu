package com.e201.global.event;

import java.util.UUID;

public record OutboxMessage(
	UUID eventId,
	String payload
) {}
