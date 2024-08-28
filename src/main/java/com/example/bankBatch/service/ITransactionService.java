package com.example.bankBatch.service;

import com.example.bankBatch.entity.Transaction;

import java.util.List;

public interface ITransactionService {

    public List<Transaction> saveAll(List<Transaction> transactionList);
}
