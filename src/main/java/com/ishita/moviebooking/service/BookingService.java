
package com.ishita.moviebooking.service;

import com.ishita.moviebooking.entity.Booking;
import com.ishita.moviebooking.entity.Seat;
import com.ishita.moviebooking.repository.BookingRepository;
import com.ishita.moviebooking.repository.SeatRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.time.LocalDateTime;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final SeatRepository seatRepository;

    public BookingService(
            BookingRepository bookingRepository,
            SeatRepository seatRepository) {
        this.bookingRepository = bookingRepository;
        this.seatRepository = seatRepository;
    }

    @Transactional
    public Booking bookSeat(Long seatId) {
        Seat seat = seatRepository.findByIdForUpdate(seatId)
                .orElseThrow(() ->
                        new RuntimeException("Seat not found"));

        if (seat.isBooked()) {
            throw new IllegalStateException(
                    "Seat is already booked!");
        }

        seat.setBooked(true);
        seatRepository.save(seat);

        Booking booking = new Booking();
        booking.setSeat(seat);
        booking.setBookingTime(LocalDateTime.now());
        booking.setStatus("CONFIRMED");

        return bookingRepository.save(booking);
    }
    public Booking getBookingById(Long bookingId) {
        return bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
    }
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }
}
