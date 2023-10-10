package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * to represent tests for the markdown class
 */
class MarkdownTest {

  Markdown m1;
  Markdown m2;
  Markdown m3;
  BasicFileAttributes attr1;
  BasicFileAttributes attr2;
  BasicFileAttributes attr3;

  /**
   * set up for tests
   */
  @BeforeEach
  public void setup() {
    Path p1 = Path.of("SampleData/Arrays.md");
    Path p2 = Path.of("SampleData/Lv1/Verctors.md");
    Path p3 = Path.of("SampleData/Lv2/Lv2-1/PA01.md");

    try {
      m1 = new Markdown(p1);
      m2 = new Markdown(p2);
      m3 = new Markdown(p3);
      attr1 = Files.readAttributes(p1, BasicFileAttributes.class);
      attr2 = Files.readAttributes(p2, BasicFileAttributes.class);
      attr3 = Files.readAttributes(p3, BasicFileAttributes.class);
    } catch (IOException e) {
    }
  }
  /**
   * Checks if creation dates are accurate and compare right
   */
  @Test
  public void testGetCreateDate() {
    assertEquals(m1.createDate, attr1.creationTime());
    assertEquals(m2.createDate, attr2.creationTime());
    assertEquals(m3.createDate, attr3.creationTime());
//    assertEquals(m1.createDate.compareTo(m2.createDate), -1);
//    assertEquals(m2.createDate.compareTo(m3.createDate), -1);
//    assertEquals(m3.createDate.compareTo(m1.createDate), 1);

  }

  /**
   * Checks the outputted summarized content of markdown files against given
   */
  @Test
  public void testGetContent() {
    assertEquals(m1.content, "# Java Arrays\n" +
        "- An **array** is a collection of variables of the same type\n" +
        "## Declaring an Array\n" +
        "- General Form: type[] arrayName;\n" +
        "- only creates a reference\n" +
        "## Creating an Array (Instantiation)\n" +
        "- General form:  arrayName = new type[numberOfElements];\n" +
        "- numberOfElements must be a positive Integer.\n" +
        "- Gotcha: Array size is not");

    assertEquals(m2.content, "# Vectors\n" +
        "- Vectors act like resizable arrays\n" +
        "## Declaring a vector\n" +
        "- General Form: Vector<type> v = new Vector();\n" +
        "- type needs to be a valid reference type\n" +
        "## Adding an element to a vector\n" +
        "- v.add(object of type);\n");

    assertEquals(m3.content, "# Plan For PA01\n" +
        "- An **array** is a collection of variables of the same type\n" +
        "## Make classes\n" +
        "- General Form: type[] arrayName;\n" +
        "## Combine MarkdownFiles\n" +
        "- General form:  arrayName = new type[numberOfElements];\n" +
        "- numberOfElements must be a positive Integer.\n" +
        "- Gotcha: Array size is not modifiable once instantiated. \n");
  }
}