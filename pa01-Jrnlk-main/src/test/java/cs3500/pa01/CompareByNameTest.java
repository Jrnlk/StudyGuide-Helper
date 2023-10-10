package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * represents tests for the compare by name class
 */
class CompareByNameTest {

  Markdown m1;
  Markdown m2;
  Markdown m3;
  /**
   * To setup tests
   */
  @BeforeEach
  void setUp() {
    Path p1 = Path.of("SampleData/Arrays.md");
    Path p2 = Path.of("SampleData/Lv1/Verctors.md");
    Path p3 = Path.of("SampleData/Lv2/Lv2-1/PA01.md");

    try {
      m1 = new Markdown(p1);
      m2 = new Markdown(p2);
      m3 = new Markdown(p3);
    } catch (IOException e) {
    }
  }

  /**
   * Checks if comparator .compare(Markdown m1, Markdown m2) method is accurate
   */
  @Test
  void compare() {
    CompareByName CN = new CompareByName();
    assertEquals(CN.compare(m1,m2), -21);
    assertEquals(CN.compare(m2,m3), 6);
    assertEquals(CN.compare(m3,m1), 15);
  }
}