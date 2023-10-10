package cs3500.pa01;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * Represents a way to traverse file system for markdowns
 */
@SuppressWarnings("checkstyle:ClassTypeParameterName")
public class TraverseSystem<Path> implements FileVisitor<Path> {

  ArrayList<Path> markdownFiles = new ArrayList<>();
  int count = 0;

  @Override
  public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
    System.out.println("Visitng: " + dir);
    return FileVisitResult.CONTINUE;
  }

  @Override
  public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
    if (file.toString().endsWith(".md")) {
      System.out.println("Markdown Found: " + file);
      count++;
      markdownFiles.add(file);
      return FileVisitResult.CONTINUE;
    } else {
      return FileVisitResult.CONTINUE;
    }
  }

  @Override
  public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
    System.err.println("Failed to visit: " + file);
    return FileVisitResult.CONTINUE;
  }

  @Override
  public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
    System.out.println("Finished Visit of: " + dir);
    return FileVisitResult.CONTINUE;
  }

  /**
   * Finds all markdown files and orders them
   *
   * @param c Given comparator will order the list of markdown accordingly
   * @return ArrayList An array list of all markdown files ordered by comparator
   */
  public ArrayList<Markdown> transformPath(Comparator<Markdown> c) {
    ArrayList<Markdown> start = new ArrayList<>();
    for (Path p : markdownFiles) {
      try {
        start.add(new Markdown((java.nio.file.Path) p));
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
      start.sort(c);
    }
    return start;
  }
}
