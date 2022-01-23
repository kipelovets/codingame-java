package ru.kipelovets;

import java.util.*;

public class Board {
  private Map<Point, Mark> moves;
  private int size;

  public enum Mark {
    X,
    O
  }
  
  public Board(int size) {
    this.size = size;
    moves = new HashMap<Point, Mark>();
  }
  public String print() {
    String result = "+" + "-".repeat(size) + "+\n";
    
    for (int i = 0; i < size; i++) {
      result += "|";
      for (int j = 0; j < size; j++) {
        Point x = new Point(i, j);
        if (moves.containsKey(x))  {
          result += moves.get(x).toString();
        } else {
          result += ".";
        }
      }
      result += "|\n";
    }
    result += "+" + "-".repeat(size) + "+";

    return result;
  }
  public void play(Mark p, Point coords) {
    moves.put(coords, p);
  }
}
