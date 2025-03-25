package com.bank.infrastructure.mapper;

import com.bank.core.domain.TaxNumber;
import com.bank.core.domain.User;
import com.bank.infrastructure.dto.request.CreateUserRequest;
import com.bank.infrastructure.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserEntity toUserEntity(User user){
        return new UserEntity(
                user.getId(),
                user.getEmain(),
                user.getPassword(),
                user.getTaxNumber().getValue(),
                user.getFullname(),
                user.getType(),
                user.getCreatedAt(),
                user.getUpdateAt()
        );
    }

    public User toUser(CreateUserRequest request) throws Exception {
        return new User(
                request.email(),
                request.password(),
                new TaxNumber(request.taxNumber()),
                request.fullname(),
                request.type()
        );
    }

    public User toUser(UserEntity userEntity) throws Exception {
        return new User(
                userEntity.getId(),
                userEntity.getEmail(),
                userEntity.getPassword(),
                new TaxNumber(userEntity.getTaxNumber()),
                userEntity.getFulname(),
                userEntity.getType(),
                userEntity.getCreatedAt(),
                userEntity.getUpdatedAt()
        );
    }
}
