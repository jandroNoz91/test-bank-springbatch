package com.example.bankBatch.service;

import com.example.bankBatch.entity.Transaction;
import com.example.bankBatch.repo.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements ITransactionService{

    @Autowired
    private TransactionRepo transactionRepo;

    @Override
    public List<Transaction> saveAll(List<Transaction> transactionList) {
        return transactionRepo.saveAll(transactionList);
    }
}
