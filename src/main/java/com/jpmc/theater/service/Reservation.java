package com.jpmc.theater.service;

import com.jpmc.theater.Theater;
import com.jpmc.theater.model.Customer;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class Reservation {
  private final Customer customer;
  private final Showing showing;
  private final int audienceCount;

  private Reservation(Customer customer, Showing showing, int audienceCount) {
    this.customer = customer;
    this.showing = showing;
    this.audienceCount = audienceCount;
  }

  public static Reservation makeNewReservation(
      Customer customer, Showing showing, int audienceCount) {
    int currentCapacity = showing.getCurrentCapacity().get();
    if (audienceCount + currentCapacity <= Theater.MAX_CAPACITY) {
      showing.getCurrentCapacity().set(currentCapacity + audienceCount);
      return new Reservation(customer, showing, audienceCount);
    } else {
      throw new IllegalStateException(
          "Can't process the reservation for the movie \""
              + showing.getMovie().getTitle()
              + "\" at "
              + showing.getStartTime()
              + ". Only "
              + (Theater.MAX_CAPACITY - currentCapacity)
              + " seats left!");
    }
  }

  public double totalFee() {
    return new BigDecimal(showing.getDiscountedMoviePrice() * audienceCount)
        .setScale(2, RoundingMode.UP)
        .doubleValue();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Reservation that = (Reservation) o;
    return audienceCount == that.audienceCount
        && Objects.equals(showing, that.showing)
        && customer.equals(((Reservation) o).customer);
  }

  @Override
  public int hashCode() {
    return Objects.hash(showing, audienceCount);
  }
}
