package ru.kipelovets.shadows_of_the_knight;

public class Rect {
  final Point topLeft;
  final Point downRight;

  public Rect(Point topLeft, Point downRight) {
    this.topLeft = topLeft;
    this.downRight = downRight;
  }

  public Rect shrink(Point pos, Point dir) {
    var tl = topLeft;
    var dr = downRight;

    if (dir.getX() > 0) {
      tl = tl.withX(pos.getX());
    }
    if (dir.getX() < 0) {
      dr = dr.withX(Math.max(pos.getX() - 1, 0));
    }
    if (dir.getY() > 0) {
      tl = tl.withY(pos.getY());
    }
    if (dir.getY() < 0) {
      dr = dr.withY(Math.max(pos.getY() - 1, 0));
    }

    return new Rect(tl, dr);
  }

  public Point middle() {
    return new Point(
        (int) Math.ceil((double) topLeft.getX() + ((double) downRight.getX() - topLeft.getX()) / 2),
        (int) Math.ceil((double) topLeft.getY() + ((double) downRight.getY() - topLeft.getY()) / 2));
  }

  public String toString() {
    return String.format("%s - %s", topLeft.toString(), downRight.toString());
  }
}