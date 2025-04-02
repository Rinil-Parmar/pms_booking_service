package com.example.controller;

import com.example.dto.ApiResponse;
import com.example.model.Booking;
import com.example.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    public ResponseEntity<ApiResponse<Booking>> createBooking(@RequestBody Booking booking) {
        ApiResponse<Booking> response = bookingService.createBooking(booking);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Booking>>> getAllBookings() {
        ApiResponse<List<Booking>> response = bookingService.getAllBookings();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Booking>> getBookingById(@PathVariable UUID id) {
        ApiResponse<Booking> response = bookingService.getBookingById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<List<Booking>>> getBookingsByUserId(@PathVariable UUID userId) {
        ApiResponse<List<Booking>> response = bookingService.getBookingsByUserId(userId);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<ApiResponse<Booking>> updateBookingStatus(@PathVariable UUID id, @RequestParam String status) {
        ApiResponse<Booking> response = bookingService.updateBookingStatus(id, status);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteBooking(@PathVariable UUID id) {
        ApiResponse<String> response = bookingService.deleteBooking(id);
        return ResponseEntity.ok(response);
    }
}
