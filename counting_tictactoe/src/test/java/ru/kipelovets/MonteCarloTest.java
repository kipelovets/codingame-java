package ru.kipelovets;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MonteCarloTest {
  Board.Mark X = Board.Mark.X;
  Board.Mark O = Board.Mark.O;

  @Test
  void testBestMove() {
    Board b = new Board(new Board.Mark[][] {
        { X, O, X },
        { O, X, O },
        { null, null, null },
    });

    MonteCarlo game = new MonteCarlo(b);
    Point bestMove = game.bestMove(new Point[] { new Point(2, 2) });
    assertEquals(new Point(2, 2), bestMove);
  }

  @Test
  void testBigBoard() {
    Board b = new Board(10);
    MoveSelector moveSelector = new MoveSelector();
    MonteCarlo game = new MonteCarlo(b);
    Point bestMove = game.bestMove(moveSelector.possibleMoves(b));
    assertEquals(new Point(0, 0), bestMove);
  }
}
