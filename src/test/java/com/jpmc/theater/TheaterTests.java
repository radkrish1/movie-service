package com.jpmc.theater;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.jpmc.theater.model.Customer;
import com.jpmc.theater.model.Schedule;
import com.jpmc.theater.service.LocalDateProvider;
import com.jpmc.theater.service.Reservation;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class TheaterTests {

  @Mock private LocalDateProvider mockLocalDateProvider = LocalDateProvider.singleton();

  @InjectMocks private Theater mockTheatre = new Theater();

  @Test
  void totalFeeForCustomer() {
    Customer john = new Customer("John Doe", "id-12345");
    Reservation reservation = mockTheatre.reserve(john, 4, 10);
    assertEquals(reservation.totalFee(), 82.5);
  }

  @Test
  void totalFeeForCustomerForDay7OfTheMonth() {
    Customer john = new Customer("John Doe", "id-12345");
    Reservation reservation = mockTheatre.reserve(john, 2, 1);
    if (mockLocalDateProvider.currentDate().getDayOfMonth() == 7) {
      assertEquals(reservation.totalFee(), 9.39);
    }
  }

  @Test
  void printMovieSchedule() {
    Theater theater = new Theater();
    List<Schedule> allSchedules = theater.formatMovieSchedule();
    StringBuilder sb = new StringBuilder();
    String expectedOut =
        "1: "
            + mockLocalDateProvider.currentDate()
            + "T09:00 Turning Red (1 hour 25 minutes) "
            + allSchedules.get(0).getPrice()
            + "\n"
            + "2: "
            + mockLocalDateProvider.currentDate()
            + "T11:00 Spider-Man: No Way Home (1 hour 30 minutes) "
            + allSchedules.get(1).getPrice()
            + "\n"
            + "3: "
            + mockLocalDateProvider.currentDate()
            + "T12:50 The Batman (1 hour 35 minutes) "
            + allSchedules.get(2).getPrice()
            + "\n"
            + "4: "
            + mockLocalDateProvider.currentDate()
            + "T14:30 Turning Red (1 hour 25 minutes) "
            + allSchedules.get(3).getPrice()
            + "\n"
            + "5: "
            + mockLocalDateProvider.currentDate()
            + "T16:10 Spider-Man: No Way Home (1 hour 30 minutes) "
            + allSchedules.get(4).getPrice()
            + "\n"
            + "6: "
            + mockLocalDateProvider.currentDate()
            + "T17:50 The Batman (1 hour 35 minutes) "
            + allSchedules.get(5).getPrice()
            + "\n"
            + "7: "
            + mockLocalDateProvider.currentDate()
            + "T19:30 Turning Red (1 hour 25 minutes) "
            + allSchedules.get(6).getPrice()
            + "\n"
            + "8: "
            + mockLocalDateProvider.currentDate()
            + "T21:10 Spider-Man: No Way Home (1 hour 30 minutes) "
            + allSchedules.get(7).getPrice()
            + "\n"
            + "9: "
            + mockLocalDateProvider.currentDate()
            + "T23:00 The Batman (1 hour 35 minutes) "
            + allSchedules.get(8).getPrice()
            + "\n";
    allSchedules.forEach(schedule -> sb.append(schedule.toString()).append("\n"));
    assertEquals(sb.toString(), expectedOut);
  }
}
