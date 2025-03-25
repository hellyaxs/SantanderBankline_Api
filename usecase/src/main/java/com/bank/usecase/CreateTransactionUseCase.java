package com.bank.usecase;

import com.bank.core.domain.Transaction;
import com.bank.core.domain.Wallet;

import java.math.BigDecimal;

public interface CreateTransactionUseCase {
    Transaction create(Wallet to, Wallet from, BigDecimal value) throws Exception;
}
