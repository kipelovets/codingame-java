package ru.kipelovets;

import java.util.Scanner;

public class Player 
{
    public static void main( String[] args )
    {
        Scanner in = new Scanner(System.in);
        Board board = new Board(10);
        MonteCarlo game = new MonteCarlo(board);

        // game loop
        while (true) {
            if (!in.hasNextInt()) {
                System.err.println("No more input");
            }
            int opponentRow = in.nextInt(); // The coordinates of your opponent's last move
            int opponentCol = in.nextInt();
            int validActionCount = in.nextInt(); // the number of possible actions for your next move
            Point[] availableMoves = new Point[validActionCount];
            for (int i = 0; i < validActionCount; i++) {
                int row = in.nextInt(); // The coordinates of a possible next move
                int col = in.nextInt();
                availableMoves[i] = new Point(col, row);
            }

            if (opponentCol != -1 && opponentRow != -1) {
                board.move(new Point(opponentCol, opponentRow));
            }

            Point move = game.bestMove(availableMoves);

            System.out.println(move.getX() + " " + move.getY()); // <row> <column>
        }
    }
}
