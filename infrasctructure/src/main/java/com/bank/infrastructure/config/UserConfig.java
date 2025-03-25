package com.bank.infrastructure.config;

import com.bank.application.gateway.CreateUserGateway;
import com.bank.application.gateway.EmailAvailableGateway;
import com.bank.application.gateway.TaxNumberAvailableGateway;
import com.bank.application.usecaseimpl.CreateUserUseCaseImpl;
import com.bank.application.usecaseimpl.EmailAvailableUseCaseImpl;
import com.bank.application.usecaseimpl.TaxNumberAvailableUseCaseImpl;
import com.bank.usecase.CreateUserUseCase;
import com.bank.usecase.EmailAvailableUseCase;
import com.bank.usecase.TaxNumberAvailableUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {

    @Bean
    public TaxNumberAvailableUseCase taxNumberAvailableUseCase(TaxNumberAvailableGateway taxNumberAvailableGateway){
        return new TaxNumberAvailableUseCaseImpl(taxNumberAvailableGateway);
    }

    @Bean
    public EmailAvailableUseCase emailAvailableUseCase(EmailAvailableGateway emailAvailableGateway){
        return new EmailAvailableUseCaseImpl(emailAvailableGateway);
    }

    @Bean
    public CreateUserUseCase createUserUseCase(CreateUserGateway createUserGateway){
        return new CreateUserUseCaseImpl(createUserGateway);
    }
}
