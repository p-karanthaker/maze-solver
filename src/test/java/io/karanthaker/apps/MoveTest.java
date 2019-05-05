package io.karanthaker.apps;

import java.io.InputStream;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;

public class MoveTest {

  private static Stream<Arguments> directionsAndCellsForOpenMaze() {
    return Stream.of(
        Arguments.of(Direction.NORTH, new Cell(1, 0)),
        Arguments.of(Direction.EAST, new Cell(2, 1)),
        Arguments.of(Direction.SOUTH, new Cell(1, 2)),
        Arguments.of(Direction.WEST, new Cell(0, 1)));
  }

  private static Stream<Arguments> wrappingMoves() {
    return Stream.of(
        Arguments.of(Direction.NORTH, new Cell(1, 0), new Cell(1, 2)),
        Arguments.of(Direction.EAST, new Cell(2, 1), new Cell(0, 1)),
        Arguments.of(Direction.SOUTH, new Cell(1, 2), new Cell(1, 0)),
        Arguments.of(Direction.WEST, new Cell(0, 1), new Cell(2, 1)));
  }

  @DisplayName("Test Moves In All Directions For Open Maze")
  @MethodSource("directionsAndCellsForOpenMaze")
  @ParameterizedTest(name = "{0}")
  public void testOpenMazeMoves(Direction direction, Cell expected) {
    testMove("open-maze.txt", direction, new Cell(1, 1), expected);
  }

  @DisplayName("Test Moves In All Directions For Closed Maze")
  @EnumSource(Direction.class)
  @ParameterizedTest(name = "{0}")
  public void testClosedMazeMoves(Direction direction) {
    Cell start = new Cell(1, 1);
    testMove("closed-maze.txt", direction, start, start);
  }

  @DisplayName("Test Move Wrapping")
  @MethodSource("wrappingMoves")
  @ParameterizedTest(name = "{0}")
  public void testWrapping(Direction direction, Cell start, Cell expected) {
    testMove("open-maze.txt", direction, start, expected);
  }

  private void testMove(String resourceName, Direction direction, Cell start, Cell expected) {
    final InputStream is = getClass().getClassLoader().getResourceAsStream(resourceName);
    Maze maze = new Maze(is);

    Cell actual = Move.check(maze, start, direction);
    Assertions.assertEquals(expected, actual);
  }
}
