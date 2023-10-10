package cs3500.pa01;

import java.io.IOException;

/**
 * Driver to produce studyguide file
 */
public class Driver {

  /**
   * Runs the make study guide method
   *
   * @param args a list with directory to walk, order of files and output directory
   */
  public static void main(String[] args) {
    StudyGuide sg = new StudyGuide(args[0], args[1], args[2]);
    sg.makeStudyGuide();
  }
}