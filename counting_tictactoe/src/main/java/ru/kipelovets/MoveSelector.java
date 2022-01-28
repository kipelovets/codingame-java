package ru.kipelovets;

import java.util.*;

public class MoveSelector {
  public Point[] possibleMoves(Board board) {
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
}
