package cs3500.pa01;

import java.util.Comparator;

/**
 * To order an array by creation date
 */
public class CompareByCreate implements Comparator<Markdown> {
  @Override
  public int compare(Markdown m1, Markdown m2) {
    return m1.createDate.compareTo(m2.createDate);
  }
}