
package com.ishita.moviebooking.controller;

import com.ishita.moviebooking.entity.Seat;
import com.ishita.moviebooking.service.SeatService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/api/seats")
public class SeatController {

    private final SeatService seatService;

    public SeatController(SeatService seatService) {
        this.seatService = seatService;
    }

    @GetMapping("/show/{showId}")
    public List<Seat> getSeatsByShow(@PathVariable Long showId) {
        return seatService.getSeatsByShow(showId);
    }

    @PostMapping
    public ResponseEntity<?> addSeat(@Valid @RequestBody Seat seat) {
        try {
            Seat savedSeat = seatService.addSeat(seat);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedSeat);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(e.getMessage());
        }
    }

    @PutMapping("/{seatId}/book")
    public ResponseEntity<?> bookSeat(@PathVariable Long seatId) {
        try {
            Seat bookedSeat = seatService.bookSeat(seatId);
            return ResponseEntity.ok(bookedSeat);
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
}

