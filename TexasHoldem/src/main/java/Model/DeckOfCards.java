package Model;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Objects;
import java.util.Random;


/**
 * Model.DeckOfCards class implementing a Model.Stack Interface
 */

public class DeckOfCards extends ADeckOfCards {

  private List<Card> deck;
  private Integer numberOfCardsInDeck = 52;


  /**
   * Model.DeckOfCards no argument Constructor to implement a Model.DeckOfCards.
   */

  public DeckOfCards() {
    this.deck = new ArrayList<>();
    this.populateDeck();

  }


  /**
   * Getter for the deck
   *
   * @return a List of Cards
   */


  public List<Card> getDeck() {
    return this.deck;
  }


  /**
   * Setter for Model.DeckOfCards helper to shuffle cards
   *
   * @param index , Index would like to exchange within the deck.
   * @param card  , Model.Card we are exchanging.
   */

  private void set(Integer index, Card card) {
    this.deck.set(index, card);

  }

  /**
   * Size of the Model.DeckOfCards
   *
   * @return number of cards in deck
   */


  public Integer size() {
    return this.deck.size();
  }


  /**
   * Tests if this Model.Stack is empty.
   *
   * @return true if and only if the Model.DeckOfCards contains no Cards. Otherwise return false.
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
   *                             Model.Stack. Note: EmptyStackException is a built-in Java
   *                             exception.
   */


  @Override
  public Card popCard() throws EmptyStackException {
    return this.deck.remove(this.size() - 1);


  }


  /**
   * Adds a Model.Card to the Model.DeckOfCards
   *
   * @param card A Model.Card push onto the Model.Stack.
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
   *                             Model.Stack. Note: EmptyStackException is a built-in Java
   *                             exception.
   */


  @Override
  public Card showTopCard() throws EmptyStackException {
    return this.deck.get(this.size() - 1);
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
   * Shuffle the Model.DeckOfCards of Cards
   * @return
   */


  public void shuffleDeck() {
    for (int i = 0; i < this.numberOfCardsInDeck; i++) {
      int index = i + (int) (Math.random() * (this.numberOfCardsInDeck - i));
      Card temp = this.deck.get(index);
      deck.set(index, this.deck.get(i));
      deck.set(i, temp);
    }

  }


  public int splitsDeck() {
    Random newTopCard = new Random();
    int indexOfNewTopCard = 0;
    for (int counter = 0; counter < 1; counter++) {
      indexOfNewTopCard = 1 + newTopCard.nextInt(this.numberOfCardsInDeck - 8);
    }
    return indexOfNewTopCard;


  }


  public DeckOfCards cutsDeck() {
    DeckOfCards deckOfCards = new DeckOfCards();
    int indexOfNewTopCard = this.splitsDeck();
    int i = this.numberOfCardsInDeck - 1;
    int j = 0;

    // We are partitioning around the new Topcard.
    // Cards that are on top of deck are being pushed to the back of the deck

    while (i > indexOfNewTopCard) {
      deckOfCards.addCard(this.popCard());
      i--;
    }

    // We then take the cards less then the partition and add it to the stack/deck

    while (j <= indexOfNewTopCard) {
      deckOfCards.addCard(this.deck.get(j));
      j++;
    }
    return deckOfCards;

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
    return "Model.DeckOfCards{" +
        "deck=" + deck +
        ", numberOfCardsInDeck=" + numberOfCardsInDeck +
        '}';
  }
}



