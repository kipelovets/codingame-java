package ru.kipelovets;

import java.util.*;

public class Game {
  private Point[] possibleMoves(Board board) {
    List<Point> result = new ArrayList<Point>();
    for (int i = 0; i < board.getSize(); i++) {
      for (int j = 0; j < board.getSize(); j++) {
        Point p = new Point(i, j);
        if (board.isCellFree(p)) {
          result.add(p);
        }
      }
    }
    return result.toArray(new Point[0]);
  }

  public int minimax(Board board, Board.Mark player) {
    return minimax(board, player, false);
  }

  private int minimax(Board board, Board.Mark player, boolean isMax) {
    if (board.isFull()) {
      int playerScore = board.calculateScore(player),
        opponentScore = board.calculateScore(player.flip());
      System.err.println(board.print());
      System.err.println("Player score: " + playerScore + ", opp score: " + opponentScore);
      return playerScore - opponentScore;
    }

    Point[] moves = possibleMoves(board);

    int bestScore = 0;
    for (Point move : moves) {
      board.move(move);
      int moveValue = minimax(board, player, !isMax);
      board.undoMove();
      if (isMax) {
        bestScore = Math.max(moveValue, bestScore);
      } else {
        bestScore = Math.min(moveValue, bestScore);
      }
    }

    return bestScore;
  }

  public Point bestMove(Board board, Board.Mark player, Point[] availableMoves) {
    Point move = null;
    int bestScore = Integer.MIN_VALUE;
    for (Point m : availableMoves) {
      System.err.println(">>> Checking move: " + m);
      board.move(m);
      int score = minimax(board, player);
      board.undoMove();;
      if (score > bestScore) {
        bestScore = score;
        move = m;
      }
    }
    return move;
  }
}
