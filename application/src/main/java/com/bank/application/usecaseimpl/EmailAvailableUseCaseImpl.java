package com.bank.application.usecaseimpl;

import com.bank.application.gateway.EmailAvailableGateway;
import com.bank.core.exception.EmailException;
import com.bank.core.exception.enums.ErrorCodeEnum;
import com.bank.usecase.EmailAvailableUseCase;
import reactor.core.publisher.Mono;

public class EmailAvailableUseCaseImpl implements EmailAvailableUseCase {
    private EmailAvailableGateway emailAvailableGateway;

    public EmailAvailableUseCaseImpl(EmailAvailableGateway emailAvailableGateway) {
        this.emailAvailableGateway = emailAvailableGateway;
    }

    @Override
    public Mono<Boolean> emailAvailableEmail(String email) throws EmailException {

        return emailAvailableGateway.emailAvailable(email).onErrorComplete(throwable -> {
            try {
                throw new EmailException(ErrorCodeEnum.ON0003.getMessage(), ErrorCodeEnum.ON0003.getCode());
            } catch (EmailException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
