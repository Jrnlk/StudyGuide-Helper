package cs3500.pa01;

import java.util.Comparator;

/**
 * To order an array by modification date
 */
public class CompareByMod implements Comparator<Markdown> {
  @Override
  public int compare(Markdown m1, Markdown m2) {
    return m1.modDate.compareTo(m2.modDate);
  }
}
