import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Objects;


/**
 * DeckOfCards class implementing a Stack Interface
 */

public class DeckOfCards implements Stack {
  private List<Card> deck;
  private Integer numberOfCardsInDeck = 52;




  /**
   * DeckOfCards no argument Constructor to implement a DeckOfCards.
   */

  public DeckOfCards() {
    this.deck = new ArrayList<>();
    this.populateDeck();
  }


  /**
   * Getter for the deck
   * @return a List of Cards
   */


  public List<Card> getDeck() {
    return this.deck;
  }


  /**
   * Setter for DeckOfCards helper to shuffle cards
   * @param index , Index would like to exchange within the deck.
   * @param card , Card we are exchanging.
   */

  private void set(Integer index, Card card) {
    this.deck.set(index, card);

  }


  /**
   * Size of the DeckOfCards
   *
   * @return number of cards in deck
   */


  public Integer size() {
    return this.deck.size();
  }

  /**
   * Return the card at a particular index
   *
   * @return Card at a specified index.
   */

  public Card cardAtPosition(Integer index) throws IndexOutOfBoundsException {
    return this.deck.get(index);
  }

  /**
   * Tests if this Stack is empty.
   *
   * @return true if and only if the DeckOfCards contains no Cards. Otherwise return false.
   */


  @Override
  public boolean isEmpty() {
    return false;
  }


  /**
   * Returns and removes the most recently-added item.
   *
   * @return The most recently-added item.
   * @throws EmptyStackException Throws an EmptyStackException if the method is called on an empty
   *                             Stack. Note: EmptyStackException is a built-in Java exception.
   */


  @Override
  public Card popCard() throws EmptyStackException {
    return this.deck.remove(this.numberOfCardsInDeck - 1);


  }


  /**
   * Adds a Card to the DeckOfCards
   *
   * @param card A Card push onto the Stack.
   */


  @Override
  public void addCard(Card card) {
    this.deck.add(card);
  }


  /**
   * Returns the most recently-added item.
   *
   * @return The most recently-added item. (Show)
   * @throws EmptyStackException Throws an EmptyStackException if the method is called on an empty
   *                             Stack. Note: EmptyStackException is a built-in Java exception.
   */


  @Override
  public Card showTopCard() throws EmptyStackException {
    return this.deck.get(this.numberOfCardsInDeck - 1);
  }



  /**
   * Populate a deck of cards.
   *
   * @return A full deck of cards.
   */

  public void populateDeck() {
    for (Suit suit : Suit.values()) {
      for (Value value : Value.values()) {
        Card newCard = new Card(suit, value);
        addCard(newCard);

      }
    }

  }


  /**
   * Shuffle the DeckOfCards of Cards
   */


  public void shuffleDeck() {
    DeckOfCards deck = new DeckOfCards();
    for (int i = 0; i < numberOfCardsInDeck; i++) {
        int index = i + (int) (Math.random() * (numberOfCardsInDeck - i));
        Card temp = deck.cardAtPosition(index);
        deck.set(index, deck.cardAtPosition(i));
        deck.set(i, temp);
        System.out.println(temp);
      }

    }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof DeckOfCards)) {
      return false;
    }
    DeckOfCards that = (DeckOfCards) o;
    return Objects.equals(deck, that.deck) &&
        Objects.equals(numberOfCardsInDeck, that.numberOfCardsInDeck);
  }



  @Override
  public int hashCode() {
    return Objects.hash(deck, numberOfCardsInDeck);
  }

  @Override
  public String toString() {
    return "DeckOfCards{" +
        "deck=" + deck +
        ", numberOfCardsInDeck=" + numberOfCardsInDeck +
        '}';
  }
}



