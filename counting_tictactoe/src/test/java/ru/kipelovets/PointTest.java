package ru.kipelovets;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class PointTest {
  @Test
  void testEquals() {
    assertTrue((new Point(0, 0)).equals(new Point(0, 0)));
    assertFalse((new Point(0, 1)).equals(new Point(0, 0)));
    assertFalse((new Point(1, 0)).equals(new Point(0, 0)));
    assertFalse((new Point(100, 100)).equals(new Point(200, 200)));
  }
}
