package com.bank.infrastructure.services;

import com.bank.application.gateway.TransferGateway;
import com.bank.core.domain.Transaction;
import com.bank.infrastructure.mapper.TransactionMapper;
import com.bank.infrastructure.mapper.WalletMapper;
import com.bank.infrastructure.repository.TransactionEntityRepository;
import com.bank.infrastructure.repository.WalletEntityRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.bank.infrastructure.utils.Utilities.log;

@Service
public class TransferGatewayImpl implements TransferGateway {
    private final WalletEntityRepository walletEntityRepository;
    private final WalletMapper walletMapper;
    private final TransactionMapper transactionMapper;
    private final TransactionEntityRepository transactionEntityRepository;

    public TransferGatewayImpl(WalletEntityRepository walletEntityRepository, WalletMapper walletMapper, TransactionMapper transactionMapper, TransactionEntityRepository transactionEntityRepository) {
        this.walletEntityRepository = walletEntityRepository;
        this.walletMapper = walletMapper;
        this.transactionMapper = transactionMapper;
        this.transactionEntityRepository = transactionEntityRepository;
    }

    @Override
    @Transactional
    public Boolean transfer(Transaction transaction) {
        try {
            log.info("Inicio da transferencia ::TransferGatewayImpl");
            walletEntityRepository.save(walletMapper.toWalletEntityUpdate(transaction.getFromWallet()));
            walletEntityRepository.save(walletMapper.toWalletEntityUpdate(transaction.getToWallet()));
            transactionEntityRepository.save(transactionMapper.concludeTransaction(transaction));
            log.info("Transferencia concluida::TransferGatewayImpl");
            return true;
        }catch (Exception e){
            transactionEntityRepository.save(transactionMapper.cancelTransaction(transaction));
            log.info("Transferencia cancelada::TransferGatewayImpl");
            return false;
        }
    }
}
