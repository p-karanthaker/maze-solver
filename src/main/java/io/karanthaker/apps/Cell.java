package io.karanthaker.apps.model;

import java.util.Objects;

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

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (!(obj instanceof Cell)) {
      return false;
    }

    Cell cell = (Cell) obj;
    return Objects.equals(x, cell.x) && Objects.equals(y, cell.y);
  }

  @Override
  public int hashCode() {
    return Objects.hash(x, y);
  }
}
