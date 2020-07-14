import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CardComparatorTest {
  Card aceCard;
  Card jackCardHearts;
  Card jackCardSpades;

  @Before
  public void setUp() throws Exception {
    aceCard = new Card(Suit.Spades, Value.ACE);
    jackCardHearts = new Card(Suit.Hearts, Value.Jack);
    jackCardSpades = new Card(Suit.Spades, Value.Jack);
  }

  @Test
  public void compareGreaterThanValue() {
    assertTrue(aceCard.compareTo(jackCardHearts) == 1);
  }

  @Test
  public void compareSameValueDifferentSuit() {
    assertTrue(jackCardHearts.compareTo(jackCardSpades) == 0);
  }

  @Test
  public void compareLessThanValue() {
    assertTrue(jackCardSpades.compareTo(aceCard) == -1);
  }


}