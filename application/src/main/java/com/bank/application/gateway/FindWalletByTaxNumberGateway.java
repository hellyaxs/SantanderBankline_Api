package com.bank.application.gateway;

import com.bank.core.domain.Wallet;

public interface FindWalletByTaxNumberGateway {
    Wallet findByTaxNumber(String taxNumber) throws Exception;
}
