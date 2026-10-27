
package com.ishita.moviebooking.repository;

import com.ishita.moviebooking.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}
