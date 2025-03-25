package com.bank.infrastructure.services;

import com.bank.application.gateway.FindWalletByTaxNumberGateway;
import com.bank.core.domain.Wallet;
import com.bank.infrastructure.mapper.WalletMapper;
import com.bank.infrastructure.repository.WalletEntityRepository;
import org.springframework.stereotype.Service;

import static com.bank.infrastructure.utils.Utilities.log;

@Service
public class FindWalletByTaxNumberGatewayImpl implements FindWalletByTaxNumberGateway {
    private WalletEntityRepository walletEntityRepository;
    private WalletMapper walletMapper;

    public FindWalletByTaxNumberGatewayImpl(WalletEntityRepository walletEntityRepository, WalletMapper walletMapper) {
        this.walletEntityRepository = walletEntityRepository;
        this.walletMapper = walletMapper;
    }

    @Override
    public Wallet findByTaxNumber(String taxNumber) throws Exception {
        log.info("Inicio da busca da carteira::FindWalletByTaxNumberGatewayImpl");
        return walletMapper.toWallet(walletEntityRepository.findByUserEntityTaxNumber(taxNumber).block());
    }
}
