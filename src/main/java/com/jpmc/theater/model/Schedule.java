package com.jpmc.theater.model;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Formatter;
import java.util.concurrent.TimeUnit;

public class Schedule {
  private final int sequence;
  private final String startTime;
  private final String movieTitle;
  private final String duration;
  private final String price;

  public Schedule(
      int sequence, LocalDateTime startTime, String movieTitle, Duration duration, double price) {
    this.sequence = sequence;
    this.startTime = startTime.toString();
    this.movieTitle = movieTitle;
    this.duration = formatTime(duration);
    this.price = "$" + new Formatter().format("%.2f", price);
  }

  private String formatTime(Duration duration) {
    long hour = duration.toHours();
    long remainingMin = duration.toMinutes() - TimeUnit.HOURS.toMinutes(duration.toHours());

    return String.format(
        "%s hour%s %s minute%s",
        hour, handlePlural(hour), remainingMin, handlePlural(remainingMin));
  }

  private String handlePlural(long value) {
    if (value == 1) {
      return "";
    } else {
      return "s";
    }
  }

  public String getPrice() {
    return this.price;
  }

  @Override
  public String toString() {
    return sequence + ": " + startTime + " " + movieTitle + " " + "(" + duration + ") " + price;
  }
}
