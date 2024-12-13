package com.example.springbatch3;


import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.step.builder.StepBuilder;

import java.util.List;

@Configuration
@EnableBatchProcessing
public class JobConfig {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;

    public JobConfig(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
        this.transactionManager = new ResourcelessTransactionManager();
    }

    // ItemReader - Reads data from a list of names
    @Bean
    public ItemReader<String> reader() {
        return new ListItemReader<>(List.of("Alice", "Bob", "Charlie"));
    }

    // ItemProcessor - Adds a greeting to each name
    @Bean
    public ItemProcessor<String, String> processor() {
        return item -> "Hello, " + item;
    }

    // ItemWriter - Writes the processed names to the console
    @Bean
    public ItemWriter<String> writer() {
        return items -> items.forEach(System.out::println);
    }

    // Step definition
    @Bean
    public Step step1() {
        return new StepBuilder("step1", jobRepository)
                .<String, String>chunk(1, transactionManager)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }

    // Job definition
    @Bean
    public Job job() {
        return new JobBuilder("job1", jobRepository)
                .start(step1())
                .build();
    }
}