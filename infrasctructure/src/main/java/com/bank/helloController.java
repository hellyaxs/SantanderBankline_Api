package com.bank;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/hello")
public class helloController {

    @GetMapping
    public Mono<String> hello() {
        return Mono.just("Hello, World!").map(String::toUpperCase);
    }
}
