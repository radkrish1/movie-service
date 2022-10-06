package com.jpmc.theater.service;

import static org.junit.jupiter.api.Assertions.assertThrows;

import com.jpmc.theater.Theater;
import com.jpmc.theater.model.Customer;
import com.jpmc.theater.model.MovieCategory;
import java.time.Duration;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

public class ReservationTests {

  @Test
  void shouldFailReservationDueToMaxCapacity() {
    var customer = new Customer("John Doe", "unused-id");
    var showing =
        new Showing(
            new Movie(
                "Spider-Man: No Way Home",
                Duration.ofMinutes(90),
                12.5,
                MovieCategory.SPECIAL_MOVIE),
            1,
            LocalDateTime.now());
    showing.getCurrentCapacity().set(Theater.MAX_CAPACITY);
    assertThrows(
        IllegalStateException.class, () -> Reservation.makeNewReservation(customer, showing, 1));
  }
}
