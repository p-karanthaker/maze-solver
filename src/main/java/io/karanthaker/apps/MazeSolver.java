package io.karanthaker.apps;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class MazeSolver {

  private static Queue<MoveCell> mainQueue = new LinkedList<>();
  private static Queue<MoveCell> tempQueue = new LinkedList<>();

  public static void solve(Maze maze) {
    mainQueue.add(new MoveCell(0, maze.getEnd()));
    tempQueue.add(new MoveCell(0, maze.getEnd()));

    boolean foundStart = false;
    while (!foundStart) {
      MoveCell toCheck = tempQueue.remove();
      List<MoveCell> toAdd = findAdjacent(maze, toCheck);

      mainQueue.forEach(moveCell -> toAdd.removeIf(
          potential -> moveCell.cell.equals(potential.cell)
              && moveCell.counter <= potential.counter));

      mainQueue.addAll(toAdd);
      tempQueue.addAll(toAdd);

      for (MoveCell moveCell : toAdd) {
        if (moveCell.cell.equals(maze.getStart())) {
          foundStart = true;
        }
      }
    }

    mainQueue.forEach(moveCell -> System.out.println(moveCell));
  }

  private static List<MoveCell> findAdjacent(Maze maze, MoveCell cellToCheck) {
    List<MoveCell> adjacentCells = new ArrayList<>();

    for (Direction direction : Direction.values()) {
      Cell cell = Move.check(maze, cellToCheck.cell, direction);
      MoveCell adj = new MoveCell(cellToCheck.counter + 1, cell);

      if (!cell.equals(cellToCheck.cell)) {
        adjacentCells.add(adj);
      }

    }
    return adjacentCells;
  }

  private static class MoveCell {

    public final int counter;
    public final Cell cell;

    public MoveCell(int counter, Cell cell) {
      this.counter = counter;
      this.cell = cell;
    }

    @Override
    public String toString() {
      return String.format("(%d,%s)", counter, cell);
    }
  }

}
