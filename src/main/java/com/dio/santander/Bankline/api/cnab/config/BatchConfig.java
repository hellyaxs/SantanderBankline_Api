package com.dio.santander.Bankline.api.cnab.config;

import com.dio.santander.Bankline.api.cnab.entities.Transation;
import com.dio.santander.Bankline.api.cnab.entities.TransationCNAB;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;


import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;

import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.transform.Range;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.core.io.FileSystemResource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

import java.math.BigDecimal;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalTime;


@Configuration
public class BatchConfig {

    private PlatformTransactionManager transactionManager;
    private JobRepository jobRepository;

    public BatchConfig(PlatformTransactionManager transactionManager, JobRepository jobRepository) {
        this.transactionManager = transactionManager;
        this.jobRepository = jobRepository;

    }

    @Bean
    Job job(Step step) {
        return new JobBuilder("job",jobRepository)
                .start(step)
                .incrementer(new RunIdIncrementer())
                .build();
    }

    @Bean
    Step step(ItemReader<TransationCNAB> itemReader, ItemProcessor<TransationCNAB, Transation> itemProcessor, ItemWriter<Transation> itemWriter) {
        return new StepBuilder("step",jobRepository)
                .<TransationCNAB,Transation>chunk(1000,transactionManager)
                .reader(itemReader)
                .processor(itemProcessor)
                .writer(itemWriter)
                .build();
    }

    @Bean
    FlatFileItemReader<TransationCNAB> itemReader() {
        return new FlatFileItemReaderBuilder<TransationCNAB>().name("itemReader")
                .resource(new FileSystemResource("files/CNAB.txt"))
                .fixedLength()
                .columns(
                        new Range(1, 1),new Range(2,9),
                        new Range(10,19),new Range(20,30),
                        new Range(31,42),new Range(43,48),
                        new Range(49,62),new Range(63,80)
                )
                .names("tipo", "data", "valor", "cpf", "cartao", "hora", "donoLoja", "nomeLoja")
                .targetType(TransationCNAB.class).build();
    }

    @Bean
    ItemProcessor<TransationCNAB,Transation> itemProcessor() {
        return item -> {
            var data = new SimpleDateFormat("yyyyMMdd").parse(item.getData());
            var dataHora = new SimpleDateFormat("HHmmss").parse(item.getHora());

           return new Transation(
                    0L,
                    item.getTipo(),
                    new Date(data.getTime()),
                    item.getValor().divide(BigDecimal.valueOf(100)),
                    item.getCpf(),
                    item.getCartao(),
                    LocalTime.of(dataHora.getHours(),dataHora.getMinutes(),dataHora.getSeconds()),
                    item.getDonoLoja(),
                    item.getNomeLoja());
        };
    }

    @Bean
    JdbcBatchItemWriter<Transation> itemWriter(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Transation>()
                .dataSource(dataSource)
                .sql("""
                       insert into TRANSATION (
                        TYPE, DATE, VALOR, CPF, CARD, TIME, STORE_OWNER, NOME_LOJA
                       ) values (
                        :type, :date, :value, :cpf, :card, :time, :storeOwner, :nomeLoja)

                     """)
                .beanMapped()
                .build();
    }





}
