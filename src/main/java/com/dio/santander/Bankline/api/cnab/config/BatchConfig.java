package com.dio.santander.Bankline.api.cnab.config;

import com.dio.santander.Bankline.api.cnab.entities.Transation;
import com.dio.santander.Bankline.api.cnab.entities.TransationCNAB;
import org.springframework.batch.core.*;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;

import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.sql.Date;
import java.sql.Time;
import java.util.Collection;


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
        return new JobBuilder("job")
                .repository(jobRepository)
                .start(step)
                .incrementer(new RunIdIncrementer())
                .build();
    }

    @Bean
    Step step(ItemReader<TransationCNAB> itemReader, ItemProcessor<TransationCNAB, Transation> itemProcessor, ItemWriter<Transation> itemWriter) {
        return new StepBuilder("step")
                .repository(jobRepository)
                .transactionManager(transactionManager)
                .<TransationCNAB,Transation>chunk(1000)
                .reader(itemReader)
                .processor(itemProcessor)
                .writer(itemWriter)
                .build();
    }

    @Bean
    public ItemProcessor<TransationCNAB, Transation> itemProcessor() {
        return item -> {
            Transation transation = new Transation();
            transation.setType(item.getType());
            transation.setDate(Date.valueOf(item.getDate()));
            transation.setValue(item.getValue());
            transation.setCpf(item.getCpf());
            transation.setCard(item.getCard());
            transation.setHour(Time.valueOf(item.getHour()));
            transation.setStoreOwner(item.getStoreOwner());
        }
    }



}
