package ru.kipelovets.shadows_of_the_knight;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class RectTest {
  @Test
  void testShrink() {
    Rect bombArea = new Rect(new Point(0, 0), new Point(1, 5));
    var newArea = bombArea.shrink(new Point(1, 2), Point.fromDirecton("UL"));
    assertEquals("(0, 0) - (0, 1)", newArea.toString());
  }
}
