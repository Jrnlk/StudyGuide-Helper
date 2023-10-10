package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Represents tests for the studyguide class
 */
class StudyGuideTest {

  Markdown m1;
  Markdown m2;
  Markdown m3;
  StudyGuide SGname;
  StudyGuide SGcreate;
  StudyGuide SGmod;

  /**
   * To setup tests
   */
  @BeforeEach
  void setUp() {
    Path start = Path.of("SampleData/");
    Path p1 = Path.of("SampleData/Arrays.md");
    Path p2 = Path.of("SampleData/Lv1/Verctors.md");
    Path p3 = Path.of("SampleData/Lv2/Lv2-1/PA01.md");
    TraverseSystem<Path> walker = new TraverseSystem<>();

    SGname = new StudyGuide("SampleData/", "name", "OutputTest");
    SGcreate = new StudyGuide("SampleData/", "created", "OutputTest");
    SGmod = new StudyGuide("SampleData/", "modified", "OutputTest");

    try {
      m1 = new Markdown(p1);
      m2 = new Markdown(p2);
      m3 = new Markdown(p3);
      Files.walkFileTree(start, walker);
    } catch (IOException e) {
    }
  }

  /**
   * Checks if study guide file has been made
   */
  @Test
  void makeStudyGuide() {
    Path out = Path.of("OutputTest/StudyGuide.md");
    SGname.makeStudyGuide();
    assertTrue(Files.exists(out));
    }

  /**
   * To check if the outputted comparator is the same as the given one
   */
  @Test
  void sortCompare() {
    StudyGuide fake = new StudyGuide("SampleData/", "", "OutputTest");
    assertTrue(SGname.sortCompare().getClass().isInstance(new CompareByName()));
    assertTrue(SGcreate.sortCompare().getClass().isInstance(new CompareByCreate()));
    assertTrue(SGmod.sortCompare().getClass().isInstance(new CompareByMod()));
    assertThrows(IllegalArgumentException.class, fake::sortCompare);
  }

  /**
   * Checks the compartor list order against given list name order
   */
  @Test
  void findFiles1() {
    assertEquals(SGname.findFiles().get(0).name, "Arrays.md");
    assertEquals(SGname.findFiles().get(1).name, "PA01.md");
    assertEquals(SGname.findFiles().get(2).name, "Verctors.md");
  }

  /**
   * Checks the compartor list order against given list name order
   */
//  @Test
//  void findFiles2() {
//    assertEquals(SGcreate.findFiles().get(0).name, "Arrays.md");
//    assertEquals(SGcreate.findFiles().get(1).name, "Verctors.md");
//    assertEquals(SGcreate.findFiles().get(2).name, "PA01.md");
//  }

//  /**
//   * Checks the compartor list order against given list name order
//   */
//  @Test
//  void findFiles3() {
//    assertEquals(SGmod.findFiles().get(0).name, "Verctors.md");
//    assertEquals(SGmod.findFiles().get(1).name, "PA01.md");
//    assertEquals(SGmod.findFiles().get(2).name, "Arrays.md");
//  }
}