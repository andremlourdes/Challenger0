package com.challenge.transaction_api.controller.core;

import com.challenge.transaction_api.business.services.Statisticsservice;
import com.challenge.transaction_api.controller.dtos.StatisticsReponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/statistic")
@RequiredArgsConstructor
public class StatisticsController {
    private final Statisticsservice statisticsservice;

    @GetMapping
    @Operation(description = "Retrieve statistics for a given interval")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Statistics retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid interval parameter"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<StatisticsReponseDTO> getStatistics(
            @RequestParam(value = "intervalSeconds", required = false, defaultValue = "60") Integer intervalSeconds) {
        return ResponseEntity.ok(statisticsservice.getStatistics(intervalSeconds));
    }
}
