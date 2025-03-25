package com.bank.infrastructure.dto.response;

import reactor.core.publisher.Mono;

import java.util.List;

public record ErrorResponse(String message, String code, List<ValidationError> validitions){

}
