package com.mpsdevelopment.biopotential.server.exceptions;

public final class NotOperationFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public NotOperationFoundException(String message) { super(message); }

	public String getCode() { return "OPERATION_NOT_FOUND"; }
}