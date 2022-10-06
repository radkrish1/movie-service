package com.jpmc.theater.utils;

import com.jpmc.theater.model.MovieCategory;
import com.jpmc.theater.service.Movie;
import com.jpmc.theater.service.Showing;
import java.time.LocalDateTime;

public class DiscountUtils {

  public static double calculateMaximumDiscount(Showing showing) {
    Movie movie = showing.getMovie();
    double maxDiscount = 0;
    double originalPrice = showing.getMovie().getTicketPrice();
    maxDiscount =
        Math.max(maxDiscount, discountOnMovieCategory(originalPrice, movie.getMovieCategory()));
    maxDiscount = Math.max(maxDiscount, discountOnMovieSequence(showing.getSequenceOfTheDay()));
    maxDiscount =
        Math.max(maxDiscount, discountOnMovieStartTime(originalPrice, showing.getStartTime()));
    maxDiscount = Math.max(maxDiscount, discountOnDate(showing.getStartTime()));

    return maxDiscount;
  }

  protected static double discountOnMovieCategory(
      double originalPrice, MovieCategory movieCategory) {
    double discount = 0;
    if (MovieCategory.SPECIAL_MOVIE.equals(movieCategory)) {
      discount = 0.2 * originalPrice;
    }
    return discount;
  }

  private static double discountOnMovieSequence(int sequence) {
    double discount;
    switch (sequence) {
      case 1:
        discount = 3;
        break;
      case 2:
        discount = 2;
        break;
      default:
        discount = 0;
    }
    return discount;
  }

  private static double discountOnMovieStartTime(
      double originalPrice, LocalDateTime movieStartTime) {
    double discount = 0;
    if (movieStartTime.getHour() >= 11 && movieStartTime.getHour() <= 16) {
      if (movieStartTime.getHour() == 16 && movieStartTime.getMinute() != 0) {
        return discount;
      }
      discount = .25 * originalPrice;
    }
    return discount;
  }

  private static double discountOnDate(LocalDateTime showTime) {
    double discount = 0;
    if (showTime.getDayOfMonth() == 7) {
      discount = 1;
    }
    return discount;
  }
}
