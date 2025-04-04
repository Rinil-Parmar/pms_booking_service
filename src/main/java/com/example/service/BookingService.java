package com.example.service;

import com.example.dto.ApiResponse;
import com.example.model.Booking;
import com.example.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;

    // Create a new booking
    public ApiResponse<Booking> createBooking(Booking booking) {
        Booking savedBooking = bookingRepository.save(booking);
        return new ApiResponse<>(true, "Booking created successfully", savedBooking);
    }

    // Get all bookings
    public ApiResponse<List<Booking>> getAllBookings() {
        List<Booking> bookings = bookingRepository.findAll();
        return new ApiResponse<>(true, "All bookings retrieved", bookings);
    }

    // Get bookings by user ID
    public ApiResponse<List<Booking>> getBookingsByUserId(UUID userId) {
        List<Booking> bookings = bookingRepository.findByUserId(userId);
        return new ApiResponse<>(true, "User bookings retrieved", bookings);
    }

    // Get booking by ID
    public ApiResponse<Booking> getBookingById(UUID bookingId) {
        Optional<Booking> bookingOptional = bookingRepository.findById(bookingId);
        return bookingOptional.map(booking ->
                        new ApiResponse<>(true, "Booking found", booking))
                .orElseGet(() -> new ApiResponse<>(false, "Booking not found", null));
    }

    public ApiResponse<Booking> updateBooking(UUID bookingId, Booking updatedBooking) {
        Optional<Booking> bookingOptional = bookingRepository.findById(bookingId);
        if (bookingOptional.isEmpty()) {
            return new ApiResponse<>(false, "Booking not found", null);
        }

        Booking existingBooking = bookingOptional.get();

        // Update only non-null fields to prevent overriding with null values
        if (updatedBooking.getReceiverName() != null) {
            existingBooking.setReceiverName(updatedBooking.getReceiverName());
        }
        if (updatedBooking.getReceiverAddress() != null) {
            existingBooking.setReceiverAddress(updatedBooking.getReceiverAddress());
        }
        if (updatedBooking.getReceiverPin() != null) {
            existingBooking.setReceiverPin(updatedBooking.getReceiverPin());
        }
        if (updatedBooking.getReceiverMobile() != null) {
            existingBooking.setReceiverMobile(updatedBooking.getReceiverMobile());
        }
        if (updatedBooking.getParcelWeightInGram() > 0) {
            existingBooking.setParcelWeightInGram(updatedBooking.getParcelWeightInGram());
        }
        if (updatedBooking.getParcelContentsDescription() != null) {
            existingBooking.setParcelContentsDescription(updatedBooking.getParcelContentsDescription());
        }
        if (updatedBooking.getParcelDeliveryType() != null) {
            existingBooking.setParcelDeliveryType(updatedBooking.getParcelDeliveryType());
        }
        if (updatedBooking.getParcelPackingPreference() != null) {
            existingBooking.setParcelPackingPreference(updatedBooking.getParcelPackingPreference());
        }
        if (updatedBooking.getParcelPickupTime() != null) {
            existingBooking.setParcelPickupTime(updatedBooking.getParcelPickupTime());
        }
        if (updatedBooking.getParcelDropoffTime() != null) {
            existingBooking.setParcelDropoffTime(updatedBooking.getParcelDropoffTime());
        }
        if (updatedBooking.getParcelServiceCost() != null) {
            existingBooking.setParcelServiceCost(updatedBooking.getParcelServiceCost());
        }
        if (updatedBooking.getStatus() != null) {
            existingBooking.setStatus(updatedBooking.getStatus());
        }

        Booking savedBooking = bookingRepository.save(existingBooking);
        return new ApiResponse<>(true, "Booking updated successfully", savedBooking);
    }

    // Update booking status
    public ApiResponse<Booking> updateBookingStatus(UUID bookingId, String newStatus) {
        Optional<Booking> bookingOptional = bookingRepository.findById(bookingId);
        if (bookingOptional.isEmpty()) {
            return new ApiResponse<>(false, "Booking not found", null);
        }
        Booking booking = bookingOptional.get();
        booking.setStatus(newStatus);
        bookingRepository.save(booking);
        return new ApiResponse<>(true, "Booking status updated", booking);
    }

    // Delete a booking
    public ApiResponse<String> deleteBooking(UUID bookingId) {
        if (!bookingRepository.existsById(bookingId)) {
            return new ApiResponse<>(false, "Booking not found", null);
        }
        bookingRepository.deleteById(bookingId);
        return new ApiResponse<>(true, "Booking deleted successfully", null);
    }
}
