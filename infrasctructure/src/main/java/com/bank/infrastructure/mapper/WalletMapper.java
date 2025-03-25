package com.bank.infrastructure.mapper;

import com.bank.core.domain.Wallet;
import com.bank.infrastructure.entity.TransactionPinEntity;
import com.bank.infrastructure.entity.UserEntity;
import com.bank.infrastructure.entity.WalletEntity;
import org.springframework.stereotype.Component;

@Component
public class WalletMapper {
    private TransactionPinMapper  transactionPinMapper;
    private UserMapper userMapper;

    public WalletMapper(TransactionPinMapper transactionPinMapper, UserMapper userMapper) {
        this.transactionPinMapper = transactionPinMapper;
        this.userMapper = userMapper;
    }

    public WalletEntity toWalletEntity(Wallet wallet, UserEntity userEntity, TransactionPinEntity transactionPinEntity) {
        return new WalletEntity(
                wallet.getBalance(),
                userEntity,
                transactionPinEntity,
                wallet.getCreatedAt(),
                wallet.getUpdateAt()
        );
    }

    public WalletEntity toWalletEntity(Wallet wallet) {
        return new WalletEntity(
                wallet.getBalance(),
                userMapper.toUserEntity(wallet.getUser()),
                transactionPinMapper.toTransactionPinEntity(wallet.getTransactionPin()),
                wallet.getCreatedAt(),
                wallet.getUpdateAt()
        );
    }

    public WalletEntity toWalletEntityUpdate(Wallet wallet) {
        return new WalletEntity(
                wallet.getId(),
                wallet.getBalance(),
                userMapper.toUserEntity(wallet.getUser()),
                transactionPinMapper.toTransactionPinEntityUpdate(wallet.getTransactionPin()),
                wallet.getCreatedAt(),
                wallet.getUpdateAt()
        );
    }

    public Wallet toWallet(WalletEntity walletEntity) throws Exception {
        if (walletEntity == null){
            return null;
        }
        return new Wallet(
                walletEntity.getId(),
                transactionPinMapper.toTransactionPin(walletEntity.getTransactionPinEntity()),
                walletEntity.getBalance(),
                userMapper.toUser(walletEntity.getUserEntity()),
                walletEntity.getCreatedAt(),
                walletEntity.getUpdatedAt()
        );
    }
}
