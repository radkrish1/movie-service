package com.jpmc.theater.service;

import com.jpmc.theater.model.MovieCategory;
import java.time.Duration;
import java.util.Objects;

public class Movie {
  private final String title;
  private final Duration runningTime;
  private final double ticketPrice;
  private final MovieCategory movieCategory;

  public Movie(
      String title, Duration runningTime, double ticketPrice, MovieCategory movieCategory) {
    this.title = title;
    this.runningTime = runningTime;
    this.ticketPrice = ticketPrice;
    this.movieCategory = movieCategory;
  }

  public String getTitle() {
    return title;
  }

  public Duration getRunningTime() {
    return runningTime;
  }

  public double getTicketPrice() {
    return ticketPrice;
  }

  public MovieCategory getMovieCategory() {
    return this.movieCategory;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Movie movie = (Movie) o;
    return Double.compare(movie.ticketPrice, ticketPrice) == 0
        && Objects.equals(title, movie.title)
        && Objects.equals(runningTime, movie.runningTime)
        && Objects.equals(movieCategory, movie.movieCategory);
  }

  @Override
  public int hashCode() {
    return Objects.hash(title, runningTime, ticketPrice, movieCategory);
  }
}
