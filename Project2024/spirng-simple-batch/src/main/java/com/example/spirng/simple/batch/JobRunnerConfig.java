package com.example.spirng.simple.batch;


import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JobRunnerConfig {


    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job createJob;

    @Bean
    public CommandLineRunner runJob() {
        return args -> {
            try {
                System.out.println("Starting the batch job...");
                jobLauncher.run(createJob, new org.springframework.batch.core.JobParameters());
                System.out.println("Batch job completed successfully!");
            } catch (JobExecutionException e) {
                System.err.println("Job failed: " + e.getMessage());
            }
        };
    }
}
