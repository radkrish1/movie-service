package com.jpmc.theater.utils;

import com.jpmc.theater.model.MovieCategory;
import com.jpmc.theater.service.Movie;
import com.jpmc.theater.service.Showing;
import java.time.Duration;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

public class DiscountUtilsTests {

  Showing showing;

  @Test
  public void shouldApplyDiscountForMovieCategory() {
    Movie movieSpecialCategory =
        new Movie(
            "A movie that will never be released",
            Duration.ofMinutes(100),
            10,
            MovieCategory.SPECIAL_MOVIE);
    showing = new Showing(movieSpecialCategory, 5, LocalDateTime.of(2022, 9, 1, 1, 1));
    assert DiscountUtils.calculateMaximumDiscount(showing) == 2.00;
  }

  @Test
  public void shouldHandleNullMovieCategory() {
    Movie noSpecialCategory =
        new Movie("A movie that will never be released", Duration.ofMinutes(100), 10, null);
    showing = new Showing(noSpecialCategory, 5, LocalDateTime.of(2022, 9, 1, 1, 1));
    assert DiscountUtils.calculateMaximumDiscount(showing) == 0.00;
  }

  @Test
  public void shouldApplyDiscountForSequenceOne() {
    Movie movieSequenceOne =
        new Movie(
            "A movie that will never be released",
            Duration.ofMinutes(100),
            10,
            MovieCategory.REGULAR);
    showing = new Showing(movieSequenceOne, 1, LocalDateTime.of(2022, 9, 1, 1, 1));
    assert DiscountUtils.calculateMaximumDiscount(showing) == 3.00;
  }

  @Test
  public void shouldApplyDiscountForSequenceTwo() {
    Movie movieSequenceTwo =
        new Movie(
            "A movie that will never be released",
            Duration.ofMinutes(100),
            10,
            MovieCategory.REGULAR);
    showing = new Showing(movieSequenceTwo, 2, LocalDateTime.of(2022, 9, 1, 1, 1));
    assert DiscountUtils.calculateMaximumDiscount(showing) == 2.00;
  }

  @Test
  public void shouldApplyDiscountForStartingTime11() {
    // Movie start time is 11:00:00 AM
    Movie movie =
        new Movie(
            "A movie that will never be released",
            Duration.ofMinutes(100),
            10,
            MovieCategory.REGULAR);
    showing = new Showing(movie, 4, LocalDateTime.of(2022, 9, 4, 11, 0));
    assert DiscountUtils.calculateMaximumDiscount(showing) == 2.50;
  }

  @Test
  public void shouldNotApplyDiscountForStartingTimeAfter4() {
    Movie movie =
        new Movie(
            "A movie that will never be released",
            Duration.ofMinutes(100),
            10,
            MovieCategory.REGULAR);
    showing = new Showing(movie, 4, LocalDateTime.of(2022, 9, 4, 16, 1));
    assert DiscountUtils.calculateMaximumDiscount(showing) == 0;
  }

  @Test
  public void shouldApplyDiscountForStartingTimeAfter11() {
    Movie movie =
        new Movie(
            "A movie that will never be released",
            Duration.ofMinutes(100),
            10,
            MovieCategory.REGULAR);
    showing = new Showing(movie, 4, LocalDateTime.of(2022, 9, 4, 12, 1));
    assert DiscountUtils.calculateMaximumDiscount(showing) == 2.50;
  }

  @Test
  public void shouldApplyDiscountForStartingTimeEndingBefore4() {
    Movie movie =
        new Movie(
            "A movie that will never be released",
            Duration.ofMinutes(100),
            10,
            MovieCategory.REGULAR);
    showing = new Showing(movie, 4, LocalDateTime.of(2022, 9, 4, 15, 59));
    assert DiscountUtils.calculateMaximumDiscount(showing) == 2.50;
  }

  @Test
  public void shouldApplyDiscountForDay7() {
    Movie movie =
        new Movie(
            "A movie that will never be released",
            Duration.ofMinutes(100),
            10,
            MovieCategory.REGULAR);
    showing = new Showing(movie, 4, LocalDateTime.of(2022, 9, 7, 18, 59));
    assert DiscountUtils.calculateMaximumDiscount(showing) == 1.00;
  }

  @Test
  public void shouldApplyMaxDiscount() {
    // Maximum discount between 7th Day of Month and "11 AM to 4 PM" conditions
    Movie movie =
        new Movie(
            "A movie that will never be released",
            Duration.ofMinutes(100),
            10,
            MovieCategory.REGULAR);
    showing = new Showing(movie, 4, LocalDateTime.of(2022, 9, 7, 12, 59));
    assert DiscountUtils.calculateMaximumDiscount(showing) == 2.50;
  }

  @Test
  public void shouldApplyMaxDiscountSpecialMovieAndSequenceNumber() {
    Movie movie =
        new Movie(
            "A movie that will never be released",
            Duration.ofMinutes(100),
            10,
            MovieCategory.SPECIAL_MOVIE);
    showing = new Showing(movie, 1, LocalDateTime.of(2022, 9, 7, 12, 59));
    assert DiscountUtils.calculateMaximumDiscount(showing) == 3.00;
  }
}
