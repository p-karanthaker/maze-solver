package io.karanthaker.apps.model;

import java.io.InputStream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

/**
 * Unit tests for the @{@link Maze} class.
 */
public class MazeTest {

  @DisplayName("Test Maze Objects Are Created Correctly")
  @CsvFileSource(resources = "/mazes/mazes.csv")
  @ParameterizedTest(name = "[{index}] {0}")
  public void testAllMazes(String mazeFile, int width, int height, int startX, int startY, int endX,
      int endY) {
    try (InputStream is = getClass().getClassLoader().getResourceAsStream("mazes/" + mazeFile)) {
      Maze maze = new Maze(is);
      Assertions.assertEquals(width, maze.getWidth());
      Assertions.assertEquals(height, maze.getHeight());
      Assertions.assertEquals(startX, maze.getStart().x);
      Assertions.assertEquals(startY, maze.getStart().y);
      Assertions.assertEquals(endX, maze.getEnd().x);
      Assertions.assertEquals(endY, maze.getEnd().y);
    } catch (Exception e) {
      Assertions.fail(e);
    }
  }
}
