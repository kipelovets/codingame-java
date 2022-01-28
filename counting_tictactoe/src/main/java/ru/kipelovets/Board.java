package ru.kipelovets;

import java.security.InvalidParameterException;
import java.util.*;

public class Board {
  private final List<Point> moves;
  private final int size;

  public enum Mark {
    X,
    O;

    public Mark flip() {
      return this == X ? O : X;
    }
  }

  public class Move {
    private final Point coords;
    private final Mark mark;

    public Move(Point coords, Mark mark) {
      this.coords = coords;
      this.mark = mark;
    }
  }

  public Board(int size) {
    this(size, new ArrayList<Point>());
  }

  public Board(Mark[][] board) {
    this(board.length);
    List<Move> extraMoves = new ArrayList<Move>();
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        if (null == board[i][j]) {
          continue;
        }
        Point p = new Point(j, i);
        if (board[i][j] == playerTurn()) {
          move(p);
        } else {
          extraMoves.add(new Move(p, board[i][j]));
        }
      }
    }
    while (extraMoves.size() > 0) {
      int found = -1;
      for (int i = 0; i < extraMoves.size(); i++) {
        Move move = extraMoves.get(i);
        if (move.mark != playerTurn()) {
          continue;
        }
        found = i;
        move(move.coords);
        extraMoves.remove(i);
        break;
      }
      if (found == -1) {
        throw new InvalidParameterException("Impossible board situation");
      }
    }
  }

  private Board(int size, List<Point> moves) {
    if (size <= 0) {
      throw new IndexOutOfBoundsException("Board size must be positive");
    }
    this.size = size;
    this.moves = moves;
  }

  Mark cellMark(Point cell) {
    int index = moves.indexOf(cell);
    if (-1 == index) {
      return null;
    }
    return index % 2 == 0 ? Mark.X : Mark.O;
  }

  public String print() {
    String result = "+" + "-".repeat(size) + "+\n";

    for (int i = 0; i < size; i++) {
      result += "|";
      for (int j = 0; j < size; j++) {
        Point x = new Point(j, i);
        if (moves.contains(x)) {
          result += cellMark(x).toString();
        } else {
          result += ".";
        }
      }
      result += "|\n";
    }
    result += "+" + "-".repeat(size) + "+";

    return result;
  }

  public void move(Point coords) {
    if (isFull()) {
      throw new IndexOutOfBoundsException("Board already full");
    }
    if (moves.contains(coords)) {
      throw new IndexOutOfBoundsException("Already occupied");
    }
    if (coords.getX() < 0 || coords.getY() < 0 || coords.getX() >= size || coords.getY() >= size) {
      throw new IndexOutOfBoundsException("Invalid coordinates");
    }

    moves.add(coords);
  }

  public void undoMove() {
    moves.remove(moves.size() - 1);
  }

  public boolean isFull() {
    return moves.size() == (size * size);
  }

  public int calculateScore(Mark player) {
    int result = 0;
    for (Point p : moves) {
      if (cellMark(p) != player) {
        continue;
      }

      if (player == cellMark(new Point(p.getX() + 1, p.getY()))
          && player == cellMark(new Point(p.getX() + 2, p.getY()))) {
        result++;
      }
      if (player == cellMark(new Point(p.getX(), p.getY() + 1))
          && player == cellMark(new Point(p.getX(), p.getY() + 2))) {
        result++;
      }
      if (player == cellMark(new Point(p.getX() + 1, p.getY() + 1))
          && player == cellMark(new Point(p.getX() + 2, p.getY() + 2))) {
        result++;
      }
    }

    return result;
  }

  public boolean isWinner(Mark player) {
    return calculateScore(player) > calculateScore(player.flip());
  }

  public boolean isCellFree(Point p) {
    return !moves.contains(p);
  }

  public int getSize() {
    return size;
  }

  public Mark playerTurn() {
    return moves.size() % 2 == 0 ? Mark.X : Mark.O;
  }
}
