package com.jpmc.theater.service;

import com.jpmc.theater.utils.DiscountUtils;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class Showing {
  private final Movie movie;
  private final int sequenceOfTheDay;
  private final LocalDateTime showStartTime;
  private final AtomicInteger currentCapacity = new AtomicInteger(0);

  public Showing(Movie movie, int sequenceOfTheDay, LocalDateTime showStartTime) {
    this.movie = movie;
    this.sequenceOfTheDay = sequenceOfTheDay;
    this.showStartTime = showStartTime;
  }

  public Movie getMovie() {
    return movie;
  }

  public LocalDateTime getStartTime() {
    return showStartTime;
  }

  public AtomicInteger getCurrentCapacity() {
    return currentCapacity;
  }

  public double getDiscountedMoviePrice() {
    double price = this.movie.getTicketPrice() - DiscountUtils.calculateMaximumDiscount(this);
    return new BigDecimal(price).setScale(2, RoundingMode.UP).doubleValue();
  }

  public int getSequenceOfTheDay() {
    return sequenceOfTheDay;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Showing showing = (Showing) o;
    return sequenceOfTheDay == showing.sequenceOfTheDay
        && Objects.equals(movie, showing.movie)
        && Objects.equals(showStartTime, showing.showStartTime)
        && Objects.equals(currentCapacity, showing.currentCapacity);
  }

  @Override
  public int hashCode() {
    return Objects.hash(movie, sequenceOfTheDay, showStartTime, currentCapacity);
  }
}
