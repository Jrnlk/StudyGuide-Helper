package cs3500.pa01;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * To represent a complete summary of all MD files
 */
public class StudyGuide {
  String startDir;
  String sort;
  String outDir;

  StudyGuide(String startDir, String sort, String outDir) {
    this.startDir = startDir;
    this.sort = sort;
    this.outDir = outDir;
  }

  /**
   * Makes file and writes information
   */
  public void makeStudyGuide() {
    Path path = Path.of(outDir + "StudyGuide" + ".md");
    StringBuilder mainContent = new StringBuilder();
    ArrayList<Markdown> markFiles = this.findFiles();

    for (Markdown m : markFiles) {
      mainContent.append(m.content);
    }
    byte[] data = mainContent.toString().getBytes();
    try {
      Files.write(path, data);
    } catch (Exception e) {
      System.out.println("File not Found");
    }
  }

  /**
   * Outputs the corresponing comparator based on given flag
   *
   * @return Comparator based of sort field of this class, the proper comparator will be outputted
   */
  public Comparator<Markdown> sortCompare() {
    switch (this.sort) {
      case "name" -> {
        return new CompareByName();
      }
      case "created" -> {
        return new CompareByCreate();
      }
      case "modified" -> {
        return new CompareByMod();
      }
      default -> {
        System.err.println("Must be sorted by name, created or modified");
        throw new IllegalArgumentException();
      }
    }
  }

  /**
   * Finds all markdown files and orders them
   *
   * @return ArrayList An array list of all markdown files in the given order
   */
  public ArrayList<Markdown> findFiles() {
    Path p = Path.of(startDir);
    TraverseSystem<Path> walker = new TraverseSystem<Path>();

    try {
      Files.walkFileTree(p, walker);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    System.out.println("MarkDown Files Found: " + walker.markdownFiles);

    return walker.transformPath(this.sortCompare());
  }
}
