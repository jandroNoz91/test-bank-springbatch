package com.example.bankBatch.step;

import com.example.bankBatch.entity.TransactionCSV;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.ClassPathResource;

import java.nio.charset.StandardCharsets;

public class TransactionItemReader extends FlatFileItemReader<TransactionCSV> {

    public TransactionItemReader(){
        setName("readTransactions");
        setResource(new ClassPathResource("transactions.csv"));
        setLinesToSkip(0);
        setEncoding(StandardCharsets.UTF_8.name());
        setLineMapper(getLineMapper());
    }

    public DefaultLineMapper<TransactionCSV> getLineMapper(){
        DefaultLineMapper<TransactionCSV> lineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();

        tokenizer.setNames("id","account","amount","type","date");
        tokenizer.setIncludedFields(0,1,2,3,4);
        tokenizer.setDelimiter(",");

        BeanWrapperFieldSetMapper<TransactionCSV> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(TransactionCSV.class);
        lineMapper.setLineTokenizer(tokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);

        return lineMapper;
    }
}
