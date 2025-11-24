package com.challenge.transaction_api.business.services;

import com.challenge.transaction_api.controller.dtos.StatisticsReponseDTO;
import com.challenge.transaction_api.controller.dtos.TransactionRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.DoubleSummaryStatistics;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class Statisticsservice {
    public final TransactionService transactionService;

    public StatisticsReponseDTO getStatistics(Integer intervalSeconds) {
        log.info("Started processing statistics calculation: {}", intervalSeconds);
        List<TransactionRequestDTO> transactions = transactionService.getTransactions(intervalSeconds);
        if (transactions.isEmpty()){
            return new StatisticsReponseDTO(0L, 0.0, 0.0, 0.0, 0.0);
        }

        DoubleSummaryStatistics summaryStatistics = transactions.stream()
                .mapToDouble(TransactionRequestDTO::value)
                .summaryStatistics();

        return new StatisticsReponseDTO(summaryStatistics.getCount(),
                summaryStatistics.getAverage(),
                summaryStatistics.getMax(),
                summaryStatistics.getMin(),
                summaryStatistics.getSum());
    }
}
