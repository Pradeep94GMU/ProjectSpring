package com.example.spirng.simple.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableBatchProcessing
public class BatchConfig {
    //this class will handle the batch proccesing


    private JobRepository jobRepository; //this is to store the all job instance and job exc histor
    //when we run a step like reading some data from a file so it shld be atomic
    //to maintain the consistency of atomicity we need to use
    private PlatformTransactionManager platformTransactionManager;

    public BatchConfig(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager) {
        this.jobRepository = jobRepository;
        this.platformTransactionManager = platformTransactionManager;
    }

    //create a Job first
    @Bean
    public Job createJob(){
        return new JobBuilder("myFirstJob", jobRepository)
                .start(myFirstJobStep())
                .build();
    }

    @Bean
    public Step myFirstJobStep() {
        return new StepBuilder("myFirstStep", jobRepository)
                .tasklet(myFirstJobTasklet(), platformTransactionManager)
                .build();
    }

    @Bean
    public Tasklet myFirstJobTasklet() {

        return (contribution, chunkContext) -> {
            System.out.println("Hello Batch ...");
            return RepeatStatus.FINISHED;
        };


    }
}
