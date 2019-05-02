package io.karanthaker.apps.model;

public class Move {

  /**
   * Finds the destination cell of a move in a given direction from the current cell on the maze.
   *
   * @param maze the Maze to check the move on.
   * @param current the current cell that is being checked.
   * @param direction the direction of movement.
   * @return the current Cell if a move in that direction is not possible, or the destination Cell
   * of that movement.
   */
  public static Cell check(Maze maze, Cell current, Direction direction) {
    int newX = current.x + direction.getX();
    int newY = current.y + direction.getY();

    newX = wrap(newX, maze.getWidth());
    newY = wrap(newY, maze.getHeight());

    if (maze.getCellValue(new Cell(newX, newY)) == Maze.WALL) {
      return current;
    }

    return new Cell(newX, newY);
  }

  /**
   * Wrap the given coordinate within the boundary given.
   *
   * @param xy the coordinate to wrap.
   * @param boundary the upper bound to wrap to.
   * @return 0 if xy > boundary-1, boundary-1 if value < 0, otherwise return xy.
   */
  private static int wrap(int xy, int boundary) {
    if (xy < 0) {
      xy = boundary - 1;
    } else if (xy > boundary - 1) {
      xy = 0;
    }

    return xy;
  }

}
