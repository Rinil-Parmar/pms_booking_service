package com.example.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class BookingResponseDTO {

    private UUID id;
    private UUID userId;
    private String receiverName;
    private String receiverAddress;
    private String receiverPin;
    private String receiverMobile;
    private int parcelWeightInGram;
    private String parcelContentsDescription;
    private String parcelDeliveryType;
    private String parcelPackingPreference;
    private LocalDateTime parcelPickupTime;
    private LocalDateTime parcelDropoffTime;
    private BigDecimal parcelServiceCost;
    private LocalDateTime parcelPaymentTime;
    private String status;
}
