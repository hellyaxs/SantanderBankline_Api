package com.bank.infrastructure.dto.response;

public record ValidationError(String field, String message) {
}
