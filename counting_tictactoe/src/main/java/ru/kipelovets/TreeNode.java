package ru.kipelovets;

import java.util.*;

public class TreeNode {
  private Point move;
  private boolean isGameEnding;
  private int wins;
  private int playouts;
  private List<TreeNode> children;
  private TreeNode parent;

  public TreeNode(TreeNode parent, Point move, boolean isGameEnding) {
    this.parent = parent;
    this.move = move;
    this.isGameEnding = isGameEnding;
    wins = 0;
    playouts = 0;
    children = new ArrayList<TreeNode>();
  }

  public TreeNode selectRandomLeafNode() {
    if (isGameEnding) {
      return null;
    }
    if (children.size() == 0 || (Math.random() < 0.3 && move.getX() != -1 && move.getY() != -1)) {
      return this;
    }
    List<TreeNode> availableChildren = new ArrayList<TreeNode>();
    for (TreeNode node : children) {
      if (node.playouts == 0) {
        availableChildren.add(node);
      }
    }
    TreeNode result = null;
    if (availableChildren.size() > 0) {
      result = findNextShuffled(availableChildren);
      if (result != null) {
        return result;
      }
    }

    result = findNextShuffled(children);

    if (result == null) {
      return this;
    }

    return result;
  }

  public void addMoves(Point[] moves, boolean finalMoves) {
    for (Point childMove : moves) {
      children.add(new TreeNode(this, childMove, finalMoves));
    }
  }

  public TreeNode selectRandomChild() {
    return children.get((int) Math.round(children.size() * Math.random()));
  }

  public Point getNextBestMove() {
    int maxIndex = -1;
    for (int i = 0; i < children.size(); i++) {
      if (maxIndex == -1 || children.get(i).wins > children.get(maxIndex).wins) {
        maxIndex = i;
      }
    }
    return maxIndex == -1 ? null : children.get(maxIndex).move;
  }

  public void recordWin() {
    wins++;
    playouts++;
    if (parent != null) {
      parent.recordLose();
    }
  }

  public void recordLose() {
    playouts++;
    if (parent != null) {
      parent.recordWin();
    }
  }

  private TreeNode findNextShuffled(List<TreeNode> availableChildren) {
    Collections.shuffle(availableChildren);
    for (TreeNode node : availableChildren) {
      TreeNode result = node.selectRandomLeafNode();
      if (result != null) {
        return result;
      }
    }
    return null;
  }

  public List<Point> movesFrom(TreeNode treeRoot) {
    if (treeRoot == this) {
      return new ArrayList<Point>();
    }

    if (parent == null) {
      throw new RuntimeException();
    }

    List<Point> parentList = parent.movesFrom(treeRoot);
    parentList.add(move);

    return parentList;
  }

  public void addPlayout(List<Point> moves, boolean lastMoveWon) {
    if (moves.size() == 0) {
      return;
    }
    boolean isLastMove = moves.size() == 1;
    TreeNode child = new TreeNode(this, moves.get(0), isLastMove);
    children.add(child);

    if (isLastMove) {
      if (lastMoveWon) {
        recordWin();
      } else {
        recordLose();
      }
      return;
    }

    moves.remove(0);
    child.addPlayout(moves, lastMoveWon);
  }

}
