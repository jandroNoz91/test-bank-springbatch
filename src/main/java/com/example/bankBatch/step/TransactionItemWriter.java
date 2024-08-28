package com.example.bankBatch.step;

import com.example.bankBatch.entity.Transaction;
import com.example.bankBatch.service.ITransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Slf4j
public class TransactionItemWriter implements ItemWriter<Transaction> {

    @Autowired
    private ITransactionService transactionService;

    @Override
    public void write(List<? extends Transaction> items) throws Exception {
        items.forEach(Transaction::toString);

        transactionService.saveAll((List<Transaction>) items);
    }
}
