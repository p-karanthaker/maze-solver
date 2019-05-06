package io.karanthaker.apps;

import java.io.InputStream;
import java.util.Scanner;

/**
 * Models a maze from a given maze file @{@link InputStream}.
 */
public class Maze {

  public static final String WALL = "#";
  public static final String PATH = " ";
  /**
   * The width of the maze.
   */
  private final int width;
  /**
   * The height of the maze.
   */
  private final int height;
  /**
   * The coordinates of the start.
   */
  private final Cell start;
  /**
   * The coordinates of the end.
   */
  private final Cell end;
  /**
   * Representation of the maze grid.
   */
  private final String[][] grid;

  /**
   * Constructs a Maze object using an input stream of a maze file.
   */
  public Maze(InputStream mazeFile) {
    final Scanner scanner = new Scanner(mazeFile);

    // TODO Validate input format
    width = scanner.nextInt();
    height = scanner.nextInt();

    int x = scanner.nextInt();
    int y = scanner.nextInt();
    start = new Cell(x, y);

    x = scanner.nextInt();
    y = scanner.nextInt();
    end = new Cell(x, y);

    grid = new String[height][width];

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int next = scanner.nextInt();
        String toAdd = PATH;
        if (next == 1) {
          toAdd = WALL;
        }

        grid[i][j] = toAdd;
      }
    }
  }

  /**
   * @return the maze width.
   */
  public int getWidth() {
    return width;
  }

  /**
   * @return the maze height.
   */
  public int getHeight() {
    return height;
  }

  /**
   * @return the coordinates of the start.
   */
  public Cell getStart() {
    return start;
  }

  /**
   * @return the coordinates of the end.
   */
  public Cell getEnd() {
    return end;
  }

  /**
   * @return 2-d array of the maze grid.
   */
  public String[][] getGrid() {
    return grid;
  }

  public String getCellValue(Cell cell) {
    return grid[cell.y][cell.x];
  }

  /**
   * @return the size, start, and end of the maze.
   */
  @Override
  public String toString() {
    return String.format("Size: %dx%d | Start: %s | End: %s",
        height,
        width,
        start,
        end
    );
  }
}
