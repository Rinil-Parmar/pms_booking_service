package com.example.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class BookingRequestDTO {

    @NotNull(message = "User ID is required")
    private UUID userId;

    @NotBlank(message = "Receiver name is required")
    private String receiverName;

    @NotBlank(message = "Receiver address is required")
    private String receiverAddress;

    @Pattern(regexp = "^[0-9]{6}$", message = "Invalid receiver PIN")
    private String receiverPin;

    @Pattern(regexp = "^[0-9]{10}$", message = "Invalid receiver mobile number")
    private String receiverMobile;

    @Min(value = 1, message = "Parcel weight must be at least 1 gram")
    private int parcelWeightInGram;

    @NotBlank(message = "Parcel contents description is required")
    private String parcelContentsDescription;

    @NotBlank(message = "Parcel delivery type is required")
    private String parcelDeliveryType;

    @NotBlank(message = "Parcel packing preference is required")
    private String parcelPackingPreference;

    @NotNull(message = "Parcel pickup time is required")
    private LocalDateTime parcelPickupTime;

    @NotNull(message = "Parcel dropoff time is required")
    private LocalDateTime parcelDropoffTime;

    @DecimalMin(value = "0.0", inclusive = false, message = "Parcel service cost must be greater than 0")
    private BigDecimal parcelServiceCost;
}
