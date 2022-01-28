package ru.kipelovets;

import java.util.*;

public class Game {
  private Board board;
  private int movesChecked;

  public Game(Board board) {
    this.board = board;
  }

  private int minimax(Board.Mark player, boolean isMax) {
    if (board.isFull()) {
      int playerScore = board.calculateScore(player),
          opponentScore = board.calculateScore(player.flip());
      // System.err.println(board.print());
      // System.err.println("Player score: " + playerScore + ", opp score: " +
      // opponentScore);

      movesChecked++;
      if (movesChecked % 1000 == 0) {
        System.err.println("Moves checked: " + movesChecked);
      }

      return playerScore - opponentScore;
    }

    Point[] moves = (new MoveSelector()).possibleMoves(board);

    int bestScore = 0;
    for (Point move : moves) {
      board.move(move);
      int moveValue = minimax(player, !isMax);
      board.undoMove();
      if (isMax) {
        bestScore = Math.max(moveValue, bestScore);
      } else {
        bestScore = Math.min(moveValue, bestScore);
      }
    }

    return bestScore;
  }

  public Point bestMove(Point[] availableMoves) {
    movesChecked = 0;
    Point move = null;
    Board.Mark player = board.playerTurn();
    int bestScore = Integer.MIN_VALUE;
    for (Point m : availableMoves) {
      System.err.println(">>> Checking move: " + m);
      board.move(m);
      int score = minimax(player, false);
      board.undoMove();
      if (score > bestScore) {
        bestScore = score;
        move = m;
      }
    }
    return move;
  }
}
