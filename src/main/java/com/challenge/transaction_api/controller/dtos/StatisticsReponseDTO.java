package com.challenge.transaction_api.controller.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

public record StatisticsReponseDTO(
        @Schema(description = "Number of transactions in the interval", example = "0") long count,
        @Schema(description = "Average value", example = "0") double avg,
        @Schema(description = "Maximum value", example = "0") double max,
        @Schema(description = "Minimum value", example = "0") double min,
        @Schema(description = "Sum of values", example = "0") double sum) {
}
