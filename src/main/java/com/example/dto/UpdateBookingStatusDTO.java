package com.example.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateBookingStatusDTO {

    @NotBlank(message = "Status is required")
    private String status;
}
