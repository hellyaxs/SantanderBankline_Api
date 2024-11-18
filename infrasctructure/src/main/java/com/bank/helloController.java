package com.bank;

import com.bank.core.domain.User;
import com.bank.infrastructure.entity.UserEntity;
import com.bank.infrastructure.repository.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;


@RestController
@RequestMapping("/hello")
public class helloController {

    @Autowired
    private UserEntityRepository repository;

    @GetMapping
    public Mono<String> hello() {
        return Mono.just("Hello, World!").map(String::toUpperCase);
    }

    @GetMapping("/users")
    public Flux<UserEntity> getUsers() {
        return repository.findAll();
    }

}
