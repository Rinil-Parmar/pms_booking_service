package com.example.repository;

import com.example.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BookingRepository extends JpaRepository<Booking, UUID> {

    // Find all bookings by user ID
    List<Booking> findByUserId(UUID userId);

    // Find a booking by ID
    Optional<Booking> findById(UUID id);

    // Check if a booking exists for a given ID
    boolean existsById(UUID id);
}
