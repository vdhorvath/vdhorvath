
import static org.junit.Assert.*;

import Model.Card;
import Model.Suit;
import Model.Value;
import org.junit.Before;
import org.junit.Test;


public class CardTest {
  Card card1;
  Card card2;
  Card card3;
  Card card4;
  Card card5;
  Card card6;

  @Before
  public void setUp() throws Exception {
    card1 = new Card(Suit.Spades, Value.King);
    card2 = new Card(Suit.Hearts, Value.Queen);
    card3 = new Card(Suit.Spades, Value.King);
    card4 = new Card(Suit.Diamonds, Value.Nine);
    card5 = new Card(Suit.Spades, Value.Four);
    card6 = new Card(Suit.Hearts, Value.ACE);



  }

  @Test
  public void getSuit() {
    assertEquals(Suit.Spades, card1.getSuit());
  }

  @Test
  public void getValue() {
    assertEquals(Value.Queen, card2.getValue());
  }

  @Test
  public void testThatCardsAreEqual() {
    assertTrue(card1.equals(card3));

  }

  @Test
  public void testThatCardsAreNotEqual() {
    assertFalse(card1.equals(card2));

  }

  @Test
  public void testCompareToLess() {
    assertEquals(-1, card2.compareTo(card3));

  }

  @Test
  public void testHashCode() {
    assertTrue(card1.hashCode() == card3.hashCode());
  }

/*
  @Test
  public void testToString() {
    String expected = "Model.Card{suit=Hearts, value=Queen}";
    assertEquals(expected, card2.toString());

  }*/





  @Test
  public void sortByValue() {
    assertTrue(card3.getValue().getValue() > card4.getValue().getValue());

  }

}