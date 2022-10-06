package com.jpmc.theater.service;

import java.time.LocalDate;

public class LocalDateProvider {
  private static LocalDateProvider instance = null;
  LocalDate currentDate;

  private LocalDateProvider() {
    currentDate = LocalDate.now();
  }

  /**
   * @return make sure to return singleton instance
   */
  public static LocalDateProvider singleton() {
    if (instance == null) {
      instance = new LocalDateProvider();
    }
    return instance;
  }

  public LocalDate currentDate() {
    return currentDate;
  }
}
