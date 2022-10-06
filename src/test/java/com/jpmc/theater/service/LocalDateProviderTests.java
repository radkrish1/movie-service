package com.jpmc.theater.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

public class LocalDateProviderTests {
  @Test
  void dateProviderShouldReturnCorrectLocalTime() {
    assertEquals(LocalDateTime.now().toLocalDate(), LocalDateProvider.singleton().currentDate());
  }
}
