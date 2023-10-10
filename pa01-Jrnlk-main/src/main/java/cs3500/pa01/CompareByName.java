package cs3500.pa01;

import java.util.Comparator;

/**
 * To order an array by name
 */
class CompareByName implements Comparator<Markdown> {
  @Override
  public int compare(Markdown m1, Markdown m2) {
    return m1.name.compareTo(m2.name);
  }
}