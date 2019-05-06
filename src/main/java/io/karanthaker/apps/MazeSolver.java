package io.karanthaker.apps;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Attempts to solve a given maze.
 */
public class MazeSolver {

  /**
   * A queue for storing the pathways.
   */
  private Queue<MoveCell> mainQueue = new LinkedList<>();

  /**
   * A temporary queue for finding potential pathways.
   */
  private Queue<MoveCell> tempQueue = new LinkedList<>();

  /**
   * The maze to solve.
   */
  private Maze maze;

  /**
   * Constructor for the MazeSolver.
   *
   * @param maze the Maze to solve.
   */
  public MazeSolver(Maze maze) {
    this.maze = maze;
  }

  /**
   * Attempts to solve the maze.
   *
   * @return true if solved, false otherwise.
   */
  public boolean solve() {
    mainQueue.add(new MoveCell(0, maze.getEnd()));
    tempQueue.add(new MoveCell(0, maze.getEnd()));

    boolean foundStart = false;
    while (!foundStart) {
      MoveCell toCheck = tempQueue.remove();
      List<MoveCell> toAdd = findAdjacent(toCheck);

      // Remove adjacent cells if they are already in the main queue and have a lower counter value.
      mainQueue.forEach(moveCell -> toAdd.removeIf(
          potential -> moveCell.cell.equals(potential.cell)
              && moveCell.counter <= potential.counter));

      // Add remaining cells to the queues.
      mainQueue.addAll(toAdd);
      tempQueue.addAll(toAdd);

      for (MoveCell moveCell : toAdd) {
        if (moveCell.cell.equals(maze.getStart())) {
          foundStart = true;
        }
      }

      // Terminate if the queue is now empty.
      if (tempQueue.isEmpty()) {
        break;
      }
    }

    return foundStart;
  }

  /**
   * Finds cells adjacent to a given cell given they can be moved to i.e. not a wall cell.
   *
   * @param cellToCheck the cell to find adjacent cells to.
   * @return a list of adjacent cells.
   */
  private List<MoveCell> findAdjacent(MoveCell cellToCheck) {
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

  /**
   * Prints out the solved maze.
   */
  public void printSolution() {
    String[][] solution = maze.getGrid();

    mainQueue.forEach(moveCell -> {
      Cell cell = moveCell.cell;
      solution[cell.y][cell.x] = Integer.toString(moveCell.counter);
    });

    List<Cell> route = new ArrayList<>();
    Cell currentCell = maze.getStart();
    route.add(currentCell);

    while (!currentCell.equals(maze.getEnd())) {
      List<MoveCell> moveCells = findAdjacent(new MoveCell(0, currentCell));
      moveCells.removeIf(moveCell -> maze.getCellValue(moveCell.cell).trim().isEmpty());

      Cell toPick = moveCells.get(0).cell;
      for (MoveCell moveCell : moveCells) {
        int a = Integer.parseInt(maze.getCellValue(moveCell.cell));
        int b = Integer.parseInt(maze.getCellValue(toPick));
        if (a < b) {
          toPick = moveCell.cell;
        }
      }

      route.add(toPick);
      currentCell = toPick;
    }

    // Replace cell value with S for the start cell, E for the end cell, and X for the path cell.
    for (Cell cell : route) {
      if (cell.equals(maze.getStart())) {
        solution[cell.y][cell.x] = "S";
      } else if (cell.equals(maze.getEnd())) {
        solution[cell.y][cell.x] = "E";
      } else {
        solution[cell.y][cell.x] = "X";
      }
    }

    // Replace cells which aren't walls, start, end, or paths with whitespace.
    for (int i = 0; i < maze.getHeight(); i++) {
      for (int j = 0; j < maze.getWidth(); j++) {
        String val = solution[i][j];
        if (val != "#" && val != "S" && val != "E" && val != "X") {
          solution[i][j] = " ";
        }
      }
    }

    for (String[] chars : solution) {
      for (String aChar : chars) {
        System.out.print(aChar);
      }
      System.out.println();
    }
  }

  /**
   * Class to store a cell and a counter. Used in finding the solved maze path.
   */
  private class MoveCell {

    /**
     * Counts distance from end cell.
     */
    public final int counter;

    /**
     * A cell in the maze.
     */
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
