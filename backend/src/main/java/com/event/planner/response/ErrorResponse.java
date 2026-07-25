package com.event.planner.response;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ErrorResponse {
	private final LocalDateTime timeStamp;
	private final Integer status;
	private final String error;
	private final String message;
}
