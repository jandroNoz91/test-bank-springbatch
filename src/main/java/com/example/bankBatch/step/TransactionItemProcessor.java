package com.example.bankBatch.step;

import com.example.bankBatch.entity.Transaction;
import com.example.bankBatch.entity.TransactionCSV;
import org.springframework.batch.item.ItemProcessor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TransactionItemProcessor implements ItemProcessor<TransactionCSV, Transaction> {
    @Override
    public Transaction process(TransactionCSV item) throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        Transaction transaction = new Transaction();
        transaction.setId(item.getId());
        transaction.setAccount(item.getAccount());
        transaction.setAmount(item.getAmount());
        transaction.setType(item.getType());
        transaction.setDate(LocalDateTime.parse(item.getDate(),formatter));
        return transaction;
    }
}
