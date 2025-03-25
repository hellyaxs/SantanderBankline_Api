package com.bank.infrastructure.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Wallets")
public class WalletEntity {

    @Column("Id")
    @Id
    private Long id;

    @Column("Balance")
    private BigDecimal balance;

    private UserEntity userEntity;

    private TransactionPinEntity transactionPinEntity;

    @Column("CreatedAt")
    private LocalDateTime createdAt;

    @Column("UpdatedAt")
    private LocalDateTime updatedAt;

    public WalletEntity(BigDecimal balance, UserEntity userEntity, TransactionPinEntity transactionPinEntity, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.balance = balance;
        this.userEntity = userEntity;
        this.transactionPinEntity = transactionPinEntity;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
