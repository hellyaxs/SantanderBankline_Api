package com.bank.usecase;

import com.bank.core.domain.Wallet;

public interface FindWalletByTaxNumberUseCase {
    Wallet findByTaxNumber(String TaxNumber) throws Exception;
}
