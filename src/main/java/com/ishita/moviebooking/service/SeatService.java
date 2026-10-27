
package com.ishita.moviebooking.service;

import com.ishita.moviebooking.entity.Seat;
import com.ishita.moviebooking.repository.SeatRepository;
import org.springframework.stereotype.Service;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;

@Service
public class SeatService {

    private final SeatRepository seatRepository;

    public SeatService(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    public List<Seat> getSeatsByShow(Long showId) {
        return seatRepository.findByShowId(showId);
    }

    public Seat addSeat(Seat seat) {
        try {
            return seatRepository.saveAndFlush(seat);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException(
                    "This seat number already exists for this show."
            );
        }
    }

    public Seat bookSeat(Long seatId) {
        Seat seat = seatRepository.findById(seatId)
                .orElseThrow(() -> new RuntimeException("Seat not found"));

        if (seat.isBooked()) {
            throw new IllegalStateException("Seat is already booked!");
        }

        seat.setBooked(true);

        return seatRepository.save(seat);
    }

}
