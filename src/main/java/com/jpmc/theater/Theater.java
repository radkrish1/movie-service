package com.jpmc.theater;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jpmc.theater.model.Customer;
import com.jpmc.theater.model.MovieCategory;
import com.jpmc.theater.model.Schedule;
import com.jpmc.theater.service.LocalDateProvider;
import com.jpmc.theater.service.Movie;
import com.jpmc.theater.service.Reservation;
import com.jpmc.theater.service.Showing;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Theater {

  public static final Integer MAX_CAPACITY = 100;
  private final List<Showing> showing;
  LocalDateProvider provider;

  public Theater() {
    this.provider = LocalDateProvider.singleton();
    showing = initializeMovieSchedules();
  }

  public static void main(String[] args) throws JsonProcessingException {
    Theater theater = new Theater();
    List<Schedule> allSchedules = theater.formatMovieSchedule();
    theater.printScheduleInTextFormat(allSchedules);
    theater.printScheduleInJsonFormat(allSchedules);
  }

  public Reservation reserve(Customer customer, int sequence, int audienceCount) {
    Showing showing;
    try {
      showing = this.showing.get(sequence - 1);
    } catch (RuntimeException ex) {
      ex.printStackTrace();
      throw new IllegalStateException(
          "Not able to find any showing for given sequence " + sequence);
    }
    return Reservation.makeNewReservation(customer, showing, audienceCount);
  }

  public List<Schedule> formatMovieSchedule() {
    List<Schedule> scheduleList = new ArrayList<>();
    this.showing.forEach(
        showing ->
            scheduleList.add(
                new Schedule(
                    showing.getSequenceOfTheDay(),
                    showing.getStartTime(),
                    showing.getMovie().getTitle(),
                    showing.getMovie().getRunningTime(),
                    showing.getDiscountedMoviePrice())));
    return scheduleList;
  }

  public void printScheduleInTextFormat(List<Schedule> allSchedules) {
    System.out.println(provider.currentDate());
    System.out.println("===========================================================");
    allSchedules.forEach(schedule -> System.out.println(schedule.toString()));
    System.out.println("===========================================================");
  }

  public void printScheduleInJsonFormat(List<Schedule> allSchedules)
      throws JsonProcessingException {
    ObjectMapper mapper = new ObjectMapper();
    System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(allSchedules));
  }

  private List<Showing> initializeMovieSchedules() {
    Movie spiderMan =
        new Movie(
            "Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, MovieCategory.SPECIAL_MOVIE);
    Movie turningRed = new Movie("Turning Red", Duration.ofMinutes(85), 11, MovieCategory.REGULAR);
    Movie theBatMan = new Movie("The Batman", Duration.ofMinutes(95), 9, MovieCategory.REGULAR);

    return List.of(
        new Showing(turningRed, 1, LocalDateTime.of(provider.currentDate(), LocalTime.of(9, 0))),
        new Showing(spiderMan, 2, LocalDateTime.of(provider.currentDate(), LocalTime.of(11, 0))),
        new Showing(theBatMan, 3, LocalDateTime.of(provider.currentDate(), LocalTime.of(12, 50))),
        new Showing(turningRed, 4, LocalDateTime.of(provider.currentDate(), LocalTime.of(14, 30))),
        new Showing(spiderMan, 5, LocalDateTime.of(provider.currentDate(), LocalTime.of(16, 10))),
        new Showing(theBatMan, 6, LocalDateTime.of(provider.currentDate(), LocalTime.of(17, 50))),
        new Showing(turningRed, 7, LocalDateTime.of(provider.currentDate(), LocalTime.of(19, 30))),
        new Showing(spiderMan, 8, LocalDateTime.of(provider.currentDate(), LocalTime.of(21, 10))),
        new Showing(theBatMan, 9, LocalDateTime.of(provider.currentDate(), LocalTime.of(23, 0))));
  }
}
