package ru.kipelovets.shadows_of_the_knight;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class PointTest {
  @Test
  void testFromDirecton() {
    assertEquals("(-1, -1)", Point.fromDirecton("UL").toString());
  }
}
