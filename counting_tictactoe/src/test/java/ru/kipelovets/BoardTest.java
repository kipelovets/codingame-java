package ru.kipelovets;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class BoardTest {
  @Test
  void testPrint() {
    Board b = new Board(10);
    b.play(Board.Mark.X, new Point(0, 0));
    b.play(Board.Mark.O, new Point(1, 0));
    b.play(Board.Mark.O, new Point(1, 5));
    b.play(Board.Mark.X, new Point(9, 5));
    b.play(Board.Mark.X, new Point(10, 5));
    String expected = """
+----------+
|X.........|
|O....O....|
|..........|
|..........|
|..........|
|..........|
|..........|
|..........|
|..........|
|.....X....|
+----------+""";
    assertEquals(expected, b.print());
  }
}
