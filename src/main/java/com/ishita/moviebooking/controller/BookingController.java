
package com.ishita.moviebooking.controller;

import com.ishita.moviebooking.entity.Booking;
import com.ishita.moviebooking.service.BookingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/seat/{seatId}")
    public ResponseEntity<?> bookSeat(@PathVariable Long seatId) {
        try {
            Booking booking = bookingService.bookSeat(seatId);
            return ResponseEntity.status(HttpStatus.CREATED).body(booking);

        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(e.getMessage());

        } catch (RuntimeException e) {
            if ("Seat not found".equals(e.getMessage())) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(e.getMessage());
            }
            throw e;
        }
    }
    @GetMapping("/{bookingId}")
    public ResponseEntity<?> getBookingById(
            @PathVariable Long bookingId) {
        try {
            Booking booking = bookingService.getBookingById(bookingId);
            return ResponseEntity.ok(booking);

        } catch (RuntimeException e) {
            if ("Booking not found".equals(e.getMessage())) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(e.getMessage());
            }
            throw e;
        }
    }
}

