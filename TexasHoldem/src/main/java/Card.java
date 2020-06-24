import java.util.Comparator;


public class Card  {
  private Suit suit;
  private Value value;


  public Card(Suit suit, Value value) {
    this.suit = suit;
    this.value = value;

  }

  public Suit getSuit() {
    return suit;
  }

  public Value getValue() {
    return value;

  }


  public static Comparator<Card> sortByValue() {
    return Comparator.comparing(card -> card.value);
  }

  public static Comparator<Card> sortBySuit() {
    return Comparator.comparing(card -> card.suit);
  }

  @Override
  public String toString() {
    return "Card{" +
        "suit=" + suit +
        ", value=" + value +
        '}';
  }
}



