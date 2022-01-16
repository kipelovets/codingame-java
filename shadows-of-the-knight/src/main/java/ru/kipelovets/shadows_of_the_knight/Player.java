package ru.kipelovets.shadows_of_the_knight;

import java.util.*;

class Player {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int W = in.nextInt(); // width of the building.
        int H = in.nextInt(); // height of the building.
        int N = in.nextInt(); // maximum number of turns before game over.
        int X0 = in.nextInt();
        int Y0 = in.nextInt();

        Point pos = new Point(X0, Y0);
        Rect bombArea = new Rect(new Point(0, 0), new Point(W - 1, H - 1));

        System.err.println(String.format("width %d, height %d, turns %d, pos %s", W, H, N, pos.toString()));

        // game loop
        while (true) {
            String bombDir = in.next();
            Point dir = Point.fromDirecton(bombDir); // the direction of the bombs from batman's current location (U, UR, R, DR, D, DL, L or UL)
            System.err.println(String.format("bomb direction %s -> %s", bombDir, dir.toString()));

            bombArea = bombArea.shrink(pos, dir);

            System.err.println(String.format("new area %s", bombArea.toString()));

            pos = bombArea.middle();
            
            System.err.println(String.format("jump to %d %d", pos.getX(), pos.getY()));

            System.out.println(String.format("%d %d", pos.getX(), pos.getY()));
        }
    }
}