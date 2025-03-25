package com.bank.infrastructure.dto.request;

import com.bank.core.domain.enums.UserTypeEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateUserRequest(@NotBlank String email, @NotBlank String password, @NotBlank String taxNumber, @NotBlank String fullname, @NotNull UserTypeEnum type, @NotBlank String pin) {
}
