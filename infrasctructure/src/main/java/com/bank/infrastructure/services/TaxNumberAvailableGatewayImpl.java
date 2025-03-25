package com.bank.infrastructure.services;

import com.bank.application.gateway.TaxNumberAvailableGateway;
import com.bank.infrastructure.repository.UserEntityRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import static com.bank.infrastructure.utils.Utilities.log;

@Service
public class TaxNumberAvailableGatewayImpl implements TaxNumberAvailableGateway {
    private UserEntityRepository userEntityRepository;

    public TaxNumberAvailableGatewayImpl(UserEntityRepository userEntityRepository) {
        this.userEntityRepository = userEntityRepository;
    }

    @Override
    public Mono<Boolean> taxNumberAvailable(String taxNumber) {
        log.info("Inicio da verificação se o TaxNumber está disponível");
        return userEntityRepository.existsByTaxNumber(taxNumber);
    }
}
