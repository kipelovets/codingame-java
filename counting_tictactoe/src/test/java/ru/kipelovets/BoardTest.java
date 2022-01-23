package ru.kipelovets;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class BoardTest {
  @Test
  void testPrint() {
    Board b = new Board(10);
    b.move(new Point(0, 0));
    b.move(new Point(1, 0));
    b.move(new Point(1, 5));
    b.move(new Point(9, 5));
    String expected = """
        +----------+
        |XO........|
        |..........|
        |..........|
        |..........|
        |..........|
        |.X.......O|
        |..........|
        |..........|
        |..........|
        |..........|
        +----------+""";
    assertEquals(expected, b.print());
  }

  @Test
  void testFull() {
    int size = 3;
    Board b = new Board(size);
    assertFalse(b.isFull());
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        b.move(new Point(i, j));
      }
    }
    assertTrue(b.isFull());
  }

  @Test
  void testScore() {
    Board b = new Board(4);
    assertEquals(0, b.calculateScore(Board.Mark.X));
    b.move(new Point(0, 0));
    b.move(new Point(3, 0));
    b.move(new Point(0, 1));
    b.move(new Point(3, 1));
    b.move(new Point(0, 2));
    b.move(new Point(3, 2));
    b.move(new Point(0, 3));
    b.move(new Point(3, 3));
    b.move(new Point(1, 0));
    b.move(new Point(2, 1));
    b.move(new Point(2, 0));
    b.move(new Point(1, 2));
    b.move(new Point(1, 1));
    b.move(new Point(1, 3));
    b.move(new Point(2, 2));
    assertEquals(4, b.calculateScore(Board.Mark.X));
    assertEquals(2, b.calculateScore(Board.Mark.O));
  }

  @Test
  void testCreatingFromArray() {
    Board.Mark X = Board.Mark.X;
    Board.Mark O = Board.Mark.O;
    Board b = new Board(new Board.Mark[][] {
        { X, O, X },
        { O, O, X },
        { null, null, null },
    });
    String expected = """
        +---+
        |XOX|
        |OOX|
        |...|
        +---+""";
    assertEquals(expected, b.print());
  }
}
