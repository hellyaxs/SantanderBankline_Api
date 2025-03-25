package com.bank.infrastructure.controllers;

import com.bank.core.exception.TransactionPinException;
import com.bank.infrastructure.dto.request.CreateUserRequest;
import com.bank.infrastructure.dto.response.BaseResponse;
import com.bank.infrastructure.mapper.UserMapper;
import com.bank.usecase.CreateUserUseCase;
import com.bank.usecase.EmailAvailableUseCase;
import com.bank.usecase.TaxNumberAvailableUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import static com.bank.infrastructure.utils.Utilities.log;

@RestController
@RequestMapping("api/v1/user")
public class UserController {

    final private CreateUserUseCase createUserUseCase;
    final private UserMapper userMapper;
    final private EmailAvailableUseCase emailAvailableUseCase;
    final private TaxNumberAvailableUseCase taxNumberAvailableUseCase;

    public UserController(CreateUserUseCase createUserUseCase, UserMapper userMapper, EmailAvailableUseCase emailAvailableUseCase, TaxNumberAvailableUseCase taxNumberAvailableUseCase) {
        this.createUserUseCase = createUserUseCase;
        this.userMapper = userMapper;
        this.emailAvailableUseCase = emailAvailableUseCase;
        this.taxNumberAvailableUseCase = taxNumberAvailableUseCase;
    }

    @PostMapping("/createUser")
//    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<BaseResponse<String>> createUser(@Valid @RequestBody CreateUserRequest request) throws Exception, TransactionPinException {
        log.info("Inicio da criação do usuário::UserController");
        emailAvailableUseCase.emailAvailableEmail(request.email());
        taxNumberAvailableUseCase.taxNumberAvaliable(request.taxNumber());
        createUserUseCase.create(userMapper.toUser(request), request.pin());
        log.info("Usuário criado com sucesso::UserController");
        return ResponseEntity.status(HttpStatus.CREATED).body(BaseResponse.<String>builder().success(true).message("Usuário criado com sucesso").build());
    }
}
