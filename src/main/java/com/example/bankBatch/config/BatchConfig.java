package com.example.bankBatch.config;

import com.example.bankBatch.entity.Transaction;
import com.example.bankBatch.entity.TransactionCSV;
import com.example.bankBatch.listener.JobListener;
import com.example.bankBatch.step.TransactionItemProcessor;
import com.example.bankBatch.step.TransactionItemReader;
import com.example.bankBatch.step.TransactionItemWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    //Item reader
    @Bean
    public TransactionItemReader itemReader(){
        return new TransactionItemReader();
    }

    //Item processor
    @Bean
    public TransactionItemProcessor itemProcessor(){
        return new TransactionItemProcessor();
    }

    //Item writer
    @Bean
    public TransactionItemWriter itemWriter(){
        return new TransactionItemWriter();
    }

    //Job listener
    @Autowired
    public JobListener jobListener;

    @Bean
    public TaskExecutor taskExecutor(){
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(1);
        taskExecutor.setMaxPoolSize(5);
        taskExecutor.setQueueCapacity(5);
        return taskExecutor;
    }

    //Step
    @Bean
    public Step readfileStep(){
        return stepBuilderFactory.get("readFile")
                .<TransactionCSV,Transaction>chunk(100)
                .reader(itemReader())
                .processor(itemProcessor())
                .writer(itemWriter())
                .taskExecutor(taskExecutor())
                .build();
    }

    //Job
    @Bean
    public Job job(){
        return  jobBuilderFactory.get("readFileWithChunk")
                .start(readfileStep())
                .listener(jobListener)
                .build();
    }

}
