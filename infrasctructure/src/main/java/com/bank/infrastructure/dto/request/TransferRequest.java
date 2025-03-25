package com.bank.infrastructure.dto.request;

import java.math.BigDecimal;

public record TransferRequest(String fromTaxNumber, String toTaxNumber, BigDecimal value, String pin) {
}
