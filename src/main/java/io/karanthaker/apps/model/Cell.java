package io.karanthaker.apps.model;

/**
 * Models a 2-d coordinate pair.
 */
public class Cell {

  /**
   * X coordinate.
   */
  public final int x;

  /**
   * Y coordinate.
   */
  public final int y;

  /**
   * Constructs a coordinate pair.
   */
  public Cell(final int x, final int y) {
    this.x = x;
    this.y = y;
  }

  /**
   * @return coordinate pair in parentheses.
   */
  @Override
  public String toString() {
    return String.format("(%d,%d)", x, y);
  }
}
