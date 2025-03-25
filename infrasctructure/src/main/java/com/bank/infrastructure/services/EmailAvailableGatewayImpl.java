package com.bank.infrastructure.services;

import com.bank.application.gateway.EmailAvailableGateway;
import com.bank.infrastructure.repository.UserEntityRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import static com.bank.infrastructure.utils.Utilities.log;

@Service
public class EmailAvailableGatewayImpl implements EmailAvailableGateway {
    private UserEntityRepository userEntityRepository;

    public EmailAvailableGatewayImpl(UserEntityRepository userEntityRepository) {
        this.userEntityRepository = userEntityRepository;
    }

    @Override
    public Mono<Boolean> emailAvailable(String email) {
        log.info("Verificação de email disponivel::EmailAvailableGatewayImpl");
        return userEntityRepository.existsByEmail(email);
    }
}
