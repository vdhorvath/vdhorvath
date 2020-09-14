package Model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

public class HandTest {

  Card card1;
  Card card2;
  Card card3;

  ArrayList<Card> cards = new ArrayList<>();
  Hand highCard;


  @Before
  public void setUp() throws Exception {
    card1 = new Card(Suit.Spades, Value.King);
    card2 = new Card(Suit.Hearts, Value.Queen);
    card3 = new Card(Suit.Spades, Value.King);

    cards.add(card1);
    cards.add(card2);
    cards.add(card3);

    highCard = new Hand(cards);


  }

  @Test
  public void size() {
  }

  @Test
  public void getHand() {
  }

  @Test
  public void addCard() {
  }

  @Test
  public void getCard() {
  }

  @Test
  public void testEquals() {
  }

  @Test
  public void testHashCode() {
  }

  @Test
  public void testToString() {
    String expected = "Your HighCard";
    assertEquals(expected, highCard.toString());
  }
}