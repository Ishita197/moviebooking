
package com.ishita.moviebooking.controller;

import com.ishita.moviebooking.entity.Show;
import com.ishita.moviebooking.service.ShowService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shows")
public class ShowController {

    private final ShowService showService;

    public ShowController(ShowService showService) {
        this.showService = showService;
    }

    @GetMapping
    public List<Show> getAllShows() {
        return showService.getAllShows();
    }

    @PostMapping
    public Show addShow(@RequestBody Show show) {
        return showService.addShow(show);
    }

    @PutMapping("/{id}")
    public Show updateShow(
            @PathVariable Long id,
            @RequestBody Show show) {
        return showService.updateShow(id, show);
    }

    @DeleteMapping("/{id}")
    public void deleteShow(@PathVariable Long id) {
        showService.deleteShow(id);
    }
}
