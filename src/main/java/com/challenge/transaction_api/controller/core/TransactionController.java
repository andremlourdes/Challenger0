package com.challenge.transaction_api.controller.core;

import com.challenge.transaction_api.business.services.Statisticsservice;
import com.challenge.transaction_api.business.services.TransactionService;
import com.challenge.transaction_api.controller.dtos.StatisticsReponseDTO;
import com.challenge.transaction_api.controller.dtos.TransactionRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/transaction")
 @SuppressWarnings("unused")
public class TransactionController {
    private final TransactionService transactionService;
    private final Statisticsservice statisticsService;

    @PostMapping
    @Operation(description = "Add a new transaction")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Transaction added successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid transaction data"),
            @ApiResponse(responseCode = "422", description = "Transaction fields do not meet requirements"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Object> addTransaction(@RequestBody TransactionRequestDTO dto) {
        transactionService.addTransaction(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @DeleteMapping
    @Operation(description = "Delete all stored transactions")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All transactions cleared successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid transaction data"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Void> clearTransactions() {
        transactionService.clearTransactions();
        return ResponseEntity.ok().build();
    }

    @GetMapping
    // Make intervalSeconds optional and default to 60 seconds, return ResponseEntity for consistency
    public ResponseEntity<StatisticsReponseDTO> getStatistics(
            @RequestParam(value = "intervalSeconds", required = false, defaultValue = "60") Integer intervalSeconds) {
        StatisticsReponseDTO dto = statisticsService.getStatistics(intervalSeconds);
        return ResponseEntity.ok(dto);
    }
}
