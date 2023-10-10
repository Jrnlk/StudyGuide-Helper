package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TraverseSystemTest {

  Markdown m1;
  Markdown m2;
  Markdown m3;

  Path p1;
  Path p2;
  Path p3;
  BasicFileAttributes attr1;
  BasicFileAttributes attr2;
  BasicFileAttributes attr3;

  TraverseSystem<Path> walker;

  @BeforeEach
  void setUp() {
    p1 = Path.of("SampleData/Arrays.md");
    p2 = Path.of("SampleData/Lv1/Verctors.md");
    p3 = Path.of("SampleData/Lv2/Lv2-1/PA01.md");

    walker = new TraverseSystem<>();

    try {
      m1 = new Markdown(p1);
      m2 = new Markdown(p2);
      m3 = new Markdown(p3);
    } catch (IOException e) {
    }
  }

  @Test
  void preVisitDirectory() {
  }

  @Test
  void visitFile() throws IOException {
    Path p4 = Path.of("SampleData/");
    Files.walkFileTree(p4, walker);
    assertEquals(walker.count, 3);
  }

  @Test
  void visitFileFailed() {
    boolean test = false;
    Path p4 = Path.of("fake/");
    try {
      walker.visitFileFailed(p4, new IOException());
    } catch (IOException e) {
      test = true;
      assertTrue(test);
    }
  }

  @Test
  void postVisitDirectory() {
  }

  @Test
  void transformPath() throws IOException {
    CompareByName CN = new CompareByName();
    CompareByCreate CC = new CompareByCreate();
    CompareByMod CM = new CompareByMod();

    Path p = Path.of("SampleData/");
    Files.walkFileTree(p, walker);
    ArrayList<Markdown> result = new ArrayList<>();
    result.add(m1);
    result.add(m3);
    result.add(m2);

    assertEquals(walker.transformPath(CN).get(0).name, result.get(0).name);
    assertEquals(walker.transformPath(CN).get(1).name, result.get(1).name);
    assertEquals(walker.transformPath(CN).get(2).name, result.get(2).name);
  }
}