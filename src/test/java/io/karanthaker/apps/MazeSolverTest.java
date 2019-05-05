package io.karanthaker.apps;

import java.io.InputStream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MazeSolverTest {

  @Test
  public void testSolver() {
    try (InputStream is = getClass().getClassLoader()
        .getResourceAsStream("sample-mazes/input.txt")) {
      Maze maze = new Maze(is);
      MazeSolver.solve(maze);
    } catch (Exception e) {
      Assertions.fail(e);
    }
  }

}
