package com.challenge.transaction_api.business.services;

import com.challenge.transaction_api.controller.dtos.TransactionRequestDTO;
import com.challenge.transaction_api.infrastructure.exceptions.UnprocessableEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionService {
    private final List<TransactionRequestDTO> listTransactions = new ArrayList<>();
    public void addTransaction(@NotNull TransactionRequestDTO dto) {
        log.info("Started processing transaction save: {}", dto);
        if(dto.dateTime().isAfter(OffsetDateTime.now())){
            log.error("Date/time is in the future");
            throw new UnprocessableEntity("Date/time is in the future");
        }
        if (dto.value()<0){
            log.error("Value cannot be less than 0");
            throw new UnprocessableEntity("Value cannot be less than 0");
        }

        listTransactions.add(dto);
        log.info("Transaction added successfully");
    }

}
