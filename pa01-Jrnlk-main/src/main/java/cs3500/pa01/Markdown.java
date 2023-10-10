package cs3500.pa01;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.Scanner;

/**
 * To represent a markdown(.md) file
 */
public class Markdown {

  String name;
  Path pa;
  FileTime createDate;
  FileTime modDate;
  String content;

  Markdown(Path p) throws IOException {
    this.pa = p;
    this.name = p.getFileName().toString();
    this.createDate = this.getCreateDate();
    this.modDate = FileTime.fromMillis(p.toFile().lastModified());
    this.content = this.getContent(p.toFile());
  }

  /**
   * Gets creationtime of file
   *
   * @return The time of creation in Filetime of file
   */
  public FileTime getCreateDate() throws IOException {
    BasicFileAttributes attr;
    attr = Files.readAttributes(this.pa, BasicFileAttributes.class);
    return attr.creationTime();
  }

  /**
   * Reads an MD file for important summary info
   *
   * @param file the MD file to be summarized
   * @return Summary of file in string
   */
  public String getContent(File file) {
    // Initialize a Scanner to read the file
    Scanner sc;
    try { // The file may not exist, in which case we need to handle that error (hence try-catch)
      sc = new Scanner(new FileInputStream(file));
    } catch (FileNotFoundException e) {
      throw new RuntimeException("File does not exist");
    }

    // Use the Scanner to iterate through the file line-by-line and accumulate its contents in a str
    StringBuilder content =
        new StringBuilder(); // StringBuilder is more efficient than String concatenation
    while (sc.hasNextLine()) { // Check there is another unread line in the file
      String line = sc.nextLine();
      int start = 0;
      int fin = 0;
      if (line.contains("[[") && line.contains("]]")) {
        fin = line.lastIndexOf("]") - 1;
        start = line.indexOf("[") + 2;
        content.append("- ").append(line, start, fin).append("\n");
      } else if (line.startsWith("#")) {
        content.append(line).append("\n");
      } else if (line.contains("[[") && !(line.contains("]]"))) {
        start = line.indexOf("[") + 2;
        fin = line.length();
        content.append("- ").append(line, start, fin);
      } else {
        continue;
      }
    }
    return content.toString();
  }
}

