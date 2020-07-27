import static org.junit.Assert.*;

import Model.Card;
import Model.DeckOfCards;
import Model.EmptyDeckOfCards;
import Model.Suit;
import Model.Value;
import org.junit.Before;
import org.junit.Test;

public class DeckOfCardsTest {

  DeckOfCards deck1;
  DeckOfCards deck2;
  Card card7;
  Card topCard;
  EmptyDeckOfCards emptyDeckOfCards;


  @Before
  public void setUp() throws Exception {
    deck1 = new DeckOfCards();
    deck2 = new DeckOfCards();
    card7 = new Card(Suit.Clubs, Value.Seven);
    topCard = new Card(Suit.Spades, Value.ACE);
    emptyDeckOfCards = new EmptyDeckOfCards();

  }


  @Test
  public void getDeck() {
    assertEquals(deck1.getDeck(), deck2.getDeck());

  }


  @Test
  public void size() {
    assertEquals(Integer.valueOf(52), deck1.size());

  }



  @Test
  public void isEmpty() {
    assertFalse(deck1.isEmpty());
  }


  @Test
  public void addCard() {
    deck2.addCard(card7);
    assertEquals(Integer.valueOf(53), deck2.size());
  }

  @Test
  public void showTopCard() {
    assertEquals(topCard, deck1.showTopCard());
  }



  @Test
  public void shuffleDeck() {
    deck2.shuffleDeck();
    deck2.getDeck();
    assertTrue(!deck1.getDeck().equals(deck2));
  }


  @Test
  public void testDeckEquals() {
    assertTrue(deck1.equals(deck2));

  }


  @Test
  public void testHashCode() {
      assertTrue(deck1.hashCode() == deck2.hashCode());
  }


  @Test
  public void testToString() {
    String expected = "Model.EmptyDeckOfCards{}";
    assertEquals(expected, emptyDeckOfCards.toString());

  }

}

