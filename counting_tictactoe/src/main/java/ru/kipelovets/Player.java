package ru.kipelovets;

import java.util.Scanner;

public class Player 
{
    public static void main( String[] args )
    {
        Scanner in = new Scanner(System.in);
        Board board = new Board(10);

        // game loop
        while (true) {
            int opponentRow = in.nextInt(); // The coordinates of your opponent's last move
            int opponentCol = in.nextInt();
            int validActionCount = in.nextInt(); // the number of possible actions for your next move
            for (int i = 0; i < validActionCount; i++) {
                int row = in.nextInt(); // The coordinates of a possible next move
                int col = in.nextInt();
            }

            if (opponentCol != -1 && opponentRow != -1) {
                board.move(new Point(opponentCol, opponentRow));
            }

            // Write an action using System.out.println()
            // To debug: System.err.println("Debug messages...");

            System.out.println("0 0"); // <row> <column>
        }
    }
}
