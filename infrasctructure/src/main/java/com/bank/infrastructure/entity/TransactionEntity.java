package com.bank.infrastructure.entity;

import com.bank.core.domain.enums.TransactionStatusEnum;

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
@Table(name = "Transactions")
public class TransactionEntity {

    @Column("Id")
    @Id
    private Long id;


    private WalletEntity fromWallet;

    private WalletEntity toWallet;

    @Column("TransactionValue")
    private BigDecimal value;

    @Column("Status")
    private TransactionStatusEnum status;

    @Column("CreatedAt")
    private LocalDateTime createdAt;

    @Column("UpdatedAt")
    private LocalDateTime updatedAt;

    public TransactionEntity(WalletEntity fromWallet, WalletEntity toWallet, BigDecimal value, TransactionStatusEnum status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.fromWallet = fromWallet;
        this.toWallet = toWallet;
        this.value = value;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
