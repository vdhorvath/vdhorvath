package Model;

import Model.Card;
import java.util.Comparator;

public class CardComparator implements Comparator<Card> {


  /**
   * Compares its two arguments for order.  Returns a negative integer, zero, or a positive integer
   * as the first argument is less than, equal to, or greater than the second.
   *
   * @param c1 the first object to be compared.
   * @param c2 the second object to be compared.
   * @return a negative integer, zero, or a positive integer as the first argument is less than,
   * equal to, or greater than the second.
   * @throws NullPointerException if an argument is null and this comparator does not permit null
   *                              arguments
   * @throws ClassCastException   if the arguments' types prevent them from being compared by this
   *                              comparator.
   */


  @Override
  public int compare(Card c1, Card c2) {
    if (c1.getValue().getValue() > c2.getValue().getValue()) {return 1;}
    if (c1.getValue().getValue() < c2.getValue().getValue()) {return -1;}
    return 0;

  }



}

