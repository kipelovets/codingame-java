package ru.kipelovets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

public class GameTest {
  Board.Mark X = Board.Mark.X;
  Board.Mark O = Board.Mark.O;

  @Test
  void testBestMove() {
    Board b = new Board(new Board.Mark[][] {
        { X, O, X },
        { O, X, O },
        { null, null, null },
    });

    Game game = new Game(b);
    Point move = game.bestMove(new Point[] {});
    assertNull(move);
    move = game.bestMove(new Point[]{new Point(0, 2), new Point(1, 2), new Point(2, 2)});
    assertEquals(new Point(0, 2), move);
  }

  @Test
  void testBigBoard() {

  }
}
