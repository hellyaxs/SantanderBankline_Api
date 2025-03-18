package com.bank.application.gateway;

import com.bank.core.domain.User;
import com.bank.core.domain.Wallet;

public interface CreateUserGateway {

    Boolean create(User user, Wallet wallet);
}
