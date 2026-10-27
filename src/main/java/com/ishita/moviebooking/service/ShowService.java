
package com.ishita.moviebooking.service;

import com.ishita.moviebooking.entity.Show;
import com.ishita.moviebooking.repository.ShowRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowService {

    private final ShowRepository showRepository;

    public ShowService(ShowRepository showRepository) {
        this.showRepository = showRepository;
    }

    public List<Show> getAllShows() {
        return showRepository.findAll();
    }

    public Show addShow(Show show) {
        return showRepository.save(show);
    }

    public Show updateShow(Long id, Show updatedShow) {
        Show existingShow = showRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Show not found"));

        existingShow.setMovie(updatedShow.getMovie());
        existingShow.setShowDateTime(updatedShow.getShowDateTime());
        existingShow.setScreenName(updatedShow.getScreenName());
        existingShow.setTotalSeats(updatedShow.getTotalSeats());

        return showRepository.save(existingShow);
    }

    public void deleteShow(Long id) {
        Show show = showRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Show not found"));

        showRepository.delete(show);
    }
}
