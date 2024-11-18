package com.bank.infrastructure.config;

import io.r2dbc.spi.Connection;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Configuration
public class DatabaseInitializer {

    @Bean
    public CommandLineRunner initDatabase(ConnectionFactory connectionFactory) {
        return args -> {
            List<String> sqlCommands = List.of(
                    "CREATE TABLE IF NOT EXISTS Users (\n" +
                            "    Id UUID NOT NULL PRIMARY KEY,\n" +
                            "    Email VARCHAR(70) NOT NULL UNIQUE,\n" +
                            "    Password VARCHAR(50) NOT NULL,\n" +
                            "    TaxNumber VARCHAR(15) NOT NULL UNIQUE,\n" +
                            "    Fullname VARCHAR(70) NOT NULL,\n" +
                            "    Type VARCHAR(30) NOT NULL,\n" +
                            "    CreatedAt TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,\n" +
                            "    UpdateAt TIMESTAMP\n" +
                            ");",

                    "CREATE TABLE IF NOT EXISTS TransactionsPin (\n" +
                            "    Id BIGSERIAL PRIMARY KEY,\n" +
                            "    Pin VARCHAR(50) NOT NULL,\n" +
                            "    Attempt INT NOT NULL,\n" +
                            "    Blocked Bool NOT NULL,\n" +
                            "    CreatedAt TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,\n" +
                            "    UpdateAt TIMESTAMP\n" +
                            ");",

                    "CREATE TABLE IF NOT EXISTS Wallets (\n" +
                            "    Id BIGSERIAL PRIMARY KEY,\n" +
                            "    Balance DECIMAL(10, 2) NOT NULL,\n" +
                            "    UserId UUID NOT NULL UNIQUE,\n" +
                            "    TransactionPinId BIGINT NOT NULL UNIQUE,\n" +
                            "    CreatedAt TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,\n" +
                            "    UpdateAt TIMESTAMP,\n" +
                            "    FOREIGN KEY (UserId) REFERENCES Users(Id),\n" +
                            "    FOREIGN KEY (TransactionPinId) REFERENCES TransactionsPin(Id)\n" +
                            ");",

                    "CREATE TABLE IF NOT EXISTS Transactions (\n" +
                            "    Id BIGSERIAL PRIMARY KEY,\n" +
                            "    FromWallet BIGINT NOT NULL,\n" +
                            "    ToWallet BIGINT NOT NULL,\n" +
                            "    TransactionValue DECIMAL(10, 2) NOT NULL,\n" +
                            "    Status VARCHAR(30) NOT NULL,\n" +
                            "    CreatedAt TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,\n" +
                            "    UpdateAt TIMESTAMP,\n" +
                            "    FOREIGN KEY (FromWallet) REFERENCES Wallets(Id),\n" +
                            "    FOREIGN KEY (ToWallet) REFERENCES Wallets(Id)\n" +
                            ");"
            );

            // Iniciando a execução dos comandos SQL de forma reativa
            Flux.fromIterable(sqlCommands)
                    .flatMap(sql -> executeSql(connectionFactory, sql)) // Para cada comando, executa de forma reativa
                    .doFinally(signalType -> System.out.println("Tabelas criadas ou já existentes"))
                    .onErrorResume(e -> {
                        System.err.println("Erro ao criar tabelas: " + e.getMessage());
                        return Mono.empty(); // Continuação mesmo após erro
                    })
                    .subscribe();
        };
    }

    private Mono<Void> executeSql(ConnectionFactory connectionFactory, String sql) {
        return Mono.from(connectionFactory.create())
                .flatMap(connection ->
                        Mono.from(connection.createStatement(sql).execute())  // Executa o SQL
                                .doFinally(signal -> connection.close()) // Fecha a conexão após execução
                )
                .then();  // Retorna um Mono<Void> após a execução do SQL
    }
}
