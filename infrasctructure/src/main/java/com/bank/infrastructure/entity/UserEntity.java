package com.bank.infrastructure.entity;

import com.bank.core.domain.enums.UserTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Users")
public class UserEntity {

    @Column("Id")
    @Id
    private UUID id;

    @Column("Email")
    private String email;

    @Column("Password")
    private String password;

    @Column("TaxNumber")
    private String taxNumber;

    @Column("Fullname")
    private String fulname;

    @Column("Type")
    private UserTypeEnum type;

    @Column("CreatedAt")
    private LocalDateTime createdAt;

    @Column("UpdatedAt")
    private LocalDateTime updatedAt;
}
