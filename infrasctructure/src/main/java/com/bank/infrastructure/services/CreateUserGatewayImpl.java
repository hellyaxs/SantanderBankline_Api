package com.bank.infrastructure.services;

import com.bank.application.gateway.CreateUserGateway;
import com.bank.core.domain.User;
import com.bank.core.domain.Wallet;
import com.bank.infrastructure.mapper.TransactionPinMapper;
import com.bank.infrastructure.mapper.UserMapper;
import com.bank.infrastructure.mapper.WalletMapper;
import com.bank.infrastructure.repository.TransactionPinEntityRepository;
import com.bank.infrastructure.repository.UserEntityRepository;
import com.bank.infrastructure.repository.WalletEntityRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import static com.bank.infrastructure.utils.Utilities.log;

@Service
public class CreateUserGatewayImpl implements CreateUserGateway {

    private final UserEntityRepository userEntityRepository;
    private final UserMapper userMapper;
    private final TransactionPinEntityRepository transactionPinEntityRepository;
    private final TransactionPinMapper transactionPinMapper;
    private final WalletEntityRepository walletEntityRepository;
    private final WalletMapper walletMapper;

    public CreateUserGatewayImpl(UserEntityRepository userEntityRepository, UserMapper userMapper, TransactionPinEntityRepository transactionPinEntityRepository, TransactionPinMapper transactionPinMapper, WalletEntityRepository walletEntityRepository, WalletMapper walletMapper) {
        this.userEntityRepository = userEntityRepository;
        this.userMapper = userMapper;
        this.transactionPinEntityRepository = transactionPinEntityRepository;
        this.transactionPinMapper = transactionPinMapper;
        this.walletEntityRepository = walletEntityRepository;
        this.walletMapper = walletMapper;
    }

    @Override
    @Transactional
    public Mono<Boolean> create(User user, Wallet wallet) {
        try {
            log.info("Inicio da criação do usuário::CreateUserGatewayImpl");
            Mono.zip(
                    userEntityRepository.save(userMapper.toUserEntity(user)),
                    transactionPinEntityRepository.save(transactionPinMapper.toTransactionPinEntity(wallet.getTransactionPin()))
                    )
                    .flatMap(tuple -> {
                        var userSaved = tuple.getT1();
                        var transactionPinSaved = tuple.getT2();
                        return walletEntityRepository.save(walletMapper.toWalletEntity(wallet, userSaved, transactionPinSaved));
                    }).subscribe();
            log.info("Usuário criado com sucesso::CreateUserGatewayImpl");
            return Mono.just(true);
        } catch (Exception e) {
            log.error("Houve um erro na criação do usuário::CreateUserGatewayImpl");
            return Mono.just(false);
        }

    }
}
