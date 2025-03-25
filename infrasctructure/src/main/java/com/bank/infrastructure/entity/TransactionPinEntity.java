package com.bank.infrastructure.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TransactionsPin")
public class TransactionPinEntity {

    @Column("Id")
    @Id
    private Long id;

    @Column("Pin")
    private String pin;

    @Column("Attempt")
    private Integer attempt;

    @Column("Blocked")
    private Boolean blocked;

    @Column("CreatedAt")
    private LocalDateTime createdAt;

    @Column("UpdatedAt")
    private LocalDateTime updatedAt;

    public TransactionPinEntity(String pin, Integer attempt, Boolean blocked, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.pin = pin;
        this.attempt = attempt;
        this.blocked = blocked;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
