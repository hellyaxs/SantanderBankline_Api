package com.bank.application.gateway;

import com.bank.core.domain.User;
import com.bank.core.domain.Wallet;
import reactor.core.publisher.Mono;

public interface CreateUserGateway {

    Mono<Boolean> create(User user, Wallet wallet);
}
