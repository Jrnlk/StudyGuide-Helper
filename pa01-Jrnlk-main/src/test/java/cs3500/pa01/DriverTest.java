package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * To check driver class
 */
class DriverTest {

  /**
   * Checks if driver class was able to produce a studyfile in the correct place
   */
  @Test
  void main() throws IOException {
    Driver d = new Driver();
    String[] args = {"SampleData/", "name", "OutputTest/"};
    d.main(args);

    Path out = Path.of("OutputTest/StudyGuide.md");
    assertTrue(Files.exists(out));
  }
}