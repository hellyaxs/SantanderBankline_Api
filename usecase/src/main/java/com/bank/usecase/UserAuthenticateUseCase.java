package com.bank.usecase;

import com.bank.core.exception.AuthenticateException;

public interface UserAuthenticateUseCase {
    Boolean authenticate(String username, String password) throws AuthenticateException;
}
