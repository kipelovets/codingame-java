package ru.kipelovets;

import java.util.*;

public class MonteCarlo {
  private Board board;
  private TreeNode treeRoot;

  public MonteCarlo(Board board) {
    this.board = board;
    treeRoot = new TreeNode(null, new Point(-1, -1), false);
  }

  public Point bestMove(Point[] availableMoves) {
    treeRoot.addMoves(availableMoves, false);

    MoveSelector moveSelector = new MoveSelector();

    for (int iterations = 0; iterations < availableMoves.length * 10; iterations++) {
      TreeNode node = treeRoot.selectRandomLeafNode();
      List<Point> movesUntilLeaf = node.movesFrom(treeRoot);
      for (Point m : movesUntilLeaf) {
        board.move(m);
      }

      List<Point> randomMoves = new ArrayList<Point>();
      while (!board.isFull()) {
        Point[] possibleMoves = moveSelector.possibleMoves(board);
        Point m = possibleMoves[(int) Math.round(Math.random() * (possibleMoves.length - 1))];
        randomMoves.add(m);
        board.move(m);
      }

      Board.Mark lastMovePlayer = board.playerTurn().flip();
      boolean lastMoveWon = board.isWinner(lastMovePlayer);
      int playoutLength = randomMoves.size();

      node.addPlayout(randomMoves, lastMoveWon);

      for (int i = 0; i < movesUntilLeaf.size() + playoutLength; i++) {
        board.undoMove();
      }
    }

    return treeRoot.getNextBestMove();
  }
}
