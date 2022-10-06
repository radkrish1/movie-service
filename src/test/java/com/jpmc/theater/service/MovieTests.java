package com.jpmc.theater.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.jpmc.theater.model.MovieCategory;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import org.junit.jupiter.api.Test;

public class MovieTests {
  @Test
  void specialMovieWith50PercentDiscount() {
    Movie spiderMan =
        new Movie(
            "Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, MovieCategory.SPECIAL_MOVIE);
    Showing showing = new Showing(spiderMan, 5, LocalDateTime.of(LocalDate.now(), LocalTime.now()));
    assertEquals(10, showing.getDiscountedMoviePrice());

    System.out.println(Duration.ofMinutes(90));
  }
}
