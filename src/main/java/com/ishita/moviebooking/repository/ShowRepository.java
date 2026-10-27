package com.ishita.moviebooking.repository;

import com.ishita.moviebooking.entity.Show;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowRepository extends JpaRepository<Show, Long> {
}
