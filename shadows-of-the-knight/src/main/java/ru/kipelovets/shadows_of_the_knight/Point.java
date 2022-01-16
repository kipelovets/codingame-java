package ru.kipelovets.shadows_of_the_knight;

public class Point {
  int x;
  int y;

  public Point(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public Point withX(int x) {
    return new Point(x, y);
  }

  public Point withY(int y) {
    return new Point(x, y);
  }

  public String toString() {
    return String.format("(%d, %d)", x, y);
  }

  static Point fromDirecton(String bombDir) {
    return new Point(
        bombDir.contains("R") ? 1 : (bombDir.contains("L") ? -1 : 0),
        bombDir.contains("D") ? 1 : (bombDir.contains("U") ? -1 : 0));
  }
}