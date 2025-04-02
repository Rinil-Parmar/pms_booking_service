package com.example.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "bookings")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull(message = "User ID is required")
    @Column(nullable = false)
    private UUID userId;

    @NotBlank(message = "Receiver name is required")
    @Column(nullable = false)
    private String receiverName;

    @NotBlank(message = "Receiver address is required")
    @Column(nullable = false)
    private String receiverAddress;

    @Pattern(regexp = "^[0-9]{6}$", message = "Invalid receiver PIN")
    @Column(nullable = false)
    private String receiverPin;

    @Pattern(regexp = "^[0-9]{10}$", message = "Invalid receiver mobile number")
    @Column(nullable = false)
    private String receiverMobile;

    @Min(value = 1, message = "Parcel weight must be at least 1 gram")
    @Column(nullable = false)
    private int parcelWeightInGram;

    @NotBlank(message = "Parcel contents description is required")
    @Column(nullable = false, length = 500)
    private String parcelContentsDescription;

    @NotBlank(message = "Parcel delivery type is required")
    @Column(nullable = false)
    private String parcelDeliveryType;

    @NotBlank(message = "Parcel packing preference is required")
    @Column(nullable = false)
    private String parcelPackingPreference;

    @NotNull(message = "Parcel pickup time is required")
    @Column(nullable = false)
    private LocalDateTime parcelPickupTime;

    @NotNull(message = "Parcel dropoff time is required")
    @Column(nullable = false)
    private LocalDateTime parcelDropoffTime;

    @DecimalMin(value = "0.0", inclusive = false, message = "Parcel service cost must be greater than 0")
    @Column(nullable = false)
    private BigDecimal parcelServiceCost;

    @Column(nullable = false, updatable = false)
    private LocalDateTime parcelPaymentTime;

    // Status field to track the booking status (default: PENDING)
    @NotBlank(message = "Booking status is required")
    @Column(nullable = false)
    @Builder.Default
    private String status = "PENDING";

    @PrePersist
    protected void onCreate() {
        parcelPaymentTime = LocalDateTime.now();
    }
}
