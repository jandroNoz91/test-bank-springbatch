package com.example.bankBatch.listener;

import com.example.bankBatch.entity.Transaction;
import com.example.bankBatch.repo.TransactionRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JobListener implements JobExecutionListener {

    private static final Logger LOG = LoggerFactory.getLogger(JobListener.class);

    @Autowired
    TransactionRepo repo;

    @Override
    public void beforeJob(JobExecution jobExecution) {

    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
            LOG.info("JOB ENDED SUCCESSFUL! Checking records: ");
            List<Transaction> transactions = repo.findAll();
            transactions.forEach(transaction -> LOG.info("Record: <"+transaction+">"));
        }
    }
}
