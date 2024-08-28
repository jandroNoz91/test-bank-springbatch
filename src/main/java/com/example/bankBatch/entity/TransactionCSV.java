package com.example.bankBatch.entity;

import lombok.Data;

@Data
public class TransactionCSV {

    private Long id;

    private Long account;

    private double amount;

    private String type;

    private String date;
}
