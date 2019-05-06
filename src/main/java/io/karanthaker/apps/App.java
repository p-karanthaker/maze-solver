package io.karanthaker.apps;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class App {

  public static void main(String[] args) {
    String answer = "Y";
    while (answer.equals("Y") || answer.equals("y")) {
      Scanner scanner = new Scanner(System.in);
      System.out.println("Enter the FULL path to the maze file:");
      String path = scanner.nextLine();
      System.out.println(path);
      File initialFile = new File(path);
      try (InputStream mazeStream = new FileInputStream(initialFile)) {
        Maze maze = new Maze(mazeStream);
        MazeSolver solver = new MazeSolver(maze);

        if (solver.solve()) {
          solver.printSolution();
        } else {
          System.out.println("This maze does not have a solution.");
        }
      } catch (FileNotFoundException fnf) {
        System.out.println("Cannot find the file specified.");
      } catch (IOException ioe) {
        ioe.printStackTrace();
      }

      System.out.println("Would you like to try again? (Y/N)");
      answer = scanner.nextLine();
    }
  }

}
