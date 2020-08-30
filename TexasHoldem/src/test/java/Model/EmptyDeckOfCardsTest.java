package Model;

import static org.junit.Assert.*;

import java.util.EmptyStackException;
import org.junit.Before;
import org.junit.Test;

public class EmptyDeckOfCardsTest {
  EmptyDeckOfCards emptyDeckOfCards;
  DeckOfCards deckOfCards;



  @Before
  public void setUp() throws Exception {
    emptyDeckOfCards = new EmptyDeckOfCards();
    deckOfCards = new DeckOfCards();

  }

  @Test
  public void isEmpty() {
    assertTrue(emptyDeckOfCards.isEmpty());
  }

  @Test
  public void size() {
    assertTrue(emptyDeckOfCards.size().equals(0));
  }

/*  @Test
  public void popCard() {
    EmptyStackException expected = new EmptyStackException();
    assertEquals(expected, emptyDeckOfCards.popCard());

  }

  @Test
  public void showTopCard() {
    EmptyStackException expected = new EmptyStackException();
    assertEquals(expected, emptyDeckOfCards.showTopCard());
  }*/

  @Test
  public void testToString() {
    String expected = "EmptyDeckOfCards{}";
    assertEquals(expected, emptyDeckOfCards.toString());


  }
}