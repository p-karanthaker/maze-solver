package io.karanthaker.apps;

import java.io.InputStream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class MazeSolverTest {

  @DisplayName("Test Mazes Can Be Solved")
  @CsvFileSource(resources = "/sample-mazes/mazes.csv")
  @ParameterizedTest(name = "[{index}] {0}")
  public void testSolver(String mazeFile) {
    try (InputStream is = getClass().getClassLoader()
        .getResourceAsStream("sample-mazes/" + mazeFile)) {
      Maze maze = new Maze(is);
      MazeSolver solver = new MazeSolver(maze);
      boolean solved = solver.solve();
      if (!solved) {
        Assertions.fail("Solvable maze could not be solved!");
      }
    } catch (Exception e) {
      Assertions.fail(e);
    }
  }

}
