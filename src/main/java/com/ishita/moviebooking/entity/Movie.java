
package com.ishita.moviebooking.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Movie title cannot be blank")
    private String title;

    @NotBlank(message = "Movie language cannot be blank")
    private String language;

    @NotNull(message = "Movie duration is required")
    @Min(value = 1, message = "Movie duration must be at least 1 minute")
    private Integer duration;

    @NotNull(message = "Movie rating is required")
    @DecimalMin(value = "0.0", message = "Rating cannot be below 0")
    @DecimalMax(value = "10.0", message = "Rating cannot exceed 10")
    private Double rating;

    public Movie() {
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }
}
