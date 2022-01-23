package ru.kipelovets;

import java.util.Objects;

public class Point {
  private int x;
  private int y;
  public Point(int x, int y) {
    this.x = x;
    this.y = y;
  }
  public int getX() {
    return x;
  }
  public void setX(int x) {
    this.x = x;
  }
  public int getY() {
    return y;
  }
  public void setY(int y) {
    this.y = y;
  }
  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }

    if (!(other instanceof Point)) {
      return false;
    }

    Point otherPoint = (Point)other;

    return x == otherPoint.x && y == otherPoint.y;
  }
  @Override
  public int hashCode() {
    return Objects.hash(x, y);
  }
}
