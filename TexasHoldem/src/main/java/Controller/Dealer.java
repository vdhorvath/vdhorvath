package Controller;

import Model.Card;
import Model.DeckOfCards;
import View.Table;

import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class Dealer {
  private DeckOfCards deckOfCards;
  private Table currTable;
  private Double gameBigBlind = 1000.0;
  private Double bigBlind = 1000.0;
  private Double smallBlind = bigBlind / 2;
  private Integer numberOfCardsInDeck = 52;



  public Dealer(DeckOfCards deckOfCards, Table currTable) {
    this.deckOfCards = deckOfCards;
    this.currTable = currTable;
  }

  /**
   * Dealer Shuffle the DeckOfCards of Cards
   * @return
   */


  public void shuffleDeck(DeckOfCards deckOfCards) {
    for (int i = 0; i < this.numberOfCardsInDeck; i++) {
      int index = i + (int) (Math.random() * (this.numberOfCardsInDeck - i));
      Card temp = deckOfCards.getCard(index);
      deckOfCards.set(index, deckOfCards.getCard(i));
      deckOfCards.set(i, temp);
    }

    System.out.println(deckOfCards);
  }


  /**
   * Dealer Splits the DeckOfCards of Cards Helper function
   * for cutting the deck of cards.
   * @return
   */


  public int splitsDeck() {
    Random newTopCard = new Random();
    int indexOfNewTopCard = 0;
    for (int counter = 0; counter < 1; counter++) {
      indexOfNewTopCard = 1 + newTopCard.nextInt(this.numberOfCardsInDeck - 8);
    }
    return indexOfNewTopCard;


  }


  /**
   * Dealer cuts the DeckOfCards of Cards
   */


  public void cutsDeck(DeckOfCards deckOfCards) {
    int indexOfNewTopCard = this.splitsDeck();
    int i = deckOfCards.size() - 1;
    int j = 0;

    // We are partitioning around the new top card.
    // Cards that are on top of deck are being pushed to the back of the deck

    while (i > indexOfNewTopCard) {
      deckOfCards.addCard(deckOfCards.popCard());
      i--;
    }


    // We then take the cards less then the partition and add it to the stack/deck

    while (j <= indexOfNewTopCard) {
      deckOfCards.addCard(deckOfCards.popCard());
      j++;
    }
    System.out.println(deckOfCards.size());
    System.out.println(deckOfCards.getDeck());



  }


  /**
   * Dealer deals the DeckOfCards of Cards
   * @return
   */


  public void deal(DeckOfCards deckOfCards, List<Players> currPlayers) {
    for (Players p : currPlayers) {
      p.addCard(deckOfCards.popCard());
      p.addCard(deckOfCards.popCard());
      System.out.println(p);
    }

  }


  /**
   * Dealer deals the flop
   * @param deckOfCards
   */


  public void dealFlop(DeckOfCards deckOfCards) {
    for (int i = 0; i < 3; i++) {
      this.currTable.addCard(deckOfCards.popCard());
    }
    System.out.println("BELOW IS THE FLOP");
    System.out.println(this.currTable.getHand());

  }

  /**
   * Dealer Deals the Flop of the River
   * param deckOfCards
   *
   */


  public void dealTurnOrRiver(DeckOfCards deckOfCards) {
    this.currTable.addCard(deckOfCards.popCard());
    System.out.println(this.currTable.getHand());
  }



  /**
   * Ask player for wager amount they would like to wager;
   * @return a double, players bet amount.
   */


  public Double askPlayerBetAmt() {
    Scanner scanner = new Scanner(System.in);
    System.out.println("_____________________________");
    System.out.println("_____________________________");
    System.out.println(String
        .format("How much would you like bet?"));
    Double betAmount = scanner.nextDouble();
    return betAmount;
  }



  /**
   * Ask player for amount they would like to wager;
   * @return a double, players bet amount.
   * @param playersArrayList
   */


  public Integer askBigBlindCheckOrBet(List<Players> playersArrayList) {
    System.out.println(playersArrayList.get(1).toCustomString());
    System.out.println("Check or Call = 1 or Raise = 2");
    Scanner scanner = new Scanner(System.in);
    Integer choice = scanner.nextInt();
    return choice;

  }


  public Integer askPlayerForChoice(Players player) {
    System.out.println("Check or Call = 1 or Raise = 2 or Fold = 3");
    Scanner scanner = new Scanner(System.in);
    Integer choice = scanner.nextInt();
    player.updateChoice(choice);
    return choice;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Dealer)) {
      return false;
    }
    Dealer dealer = (Dealer) o;
    return Objects.equals(deckOfCards, dealer.deckOfCards) &&
        Objects.equals(currTable, dealer.currTable) &&
        Objects.equals(gameBigBlind, dealer.gameBigBlind) &&
        Objects.equals(bigBlind, dealer.bigBlind) &&
        Objects.equals(smallBlind, dealer.smallBlind) &&
        Objects.equals(numberOfCardsInDeck, dealer.numberOfCardsInDeck);
  }

  @Override
  public int hashCode() {
    return Objects
        .hash(deckOfCards, currTable, gameBigBlind, bigBlind, smallBlind, numberOfCardsInDeck);
  }


  @Override
  public String toString() {
    return "Dealer{" +
        "deckOfCards=" + deckOfCards +
        ", currTable=" + currTable +
        ", gameBigBlind=" + gameBigBlind +
        ", bigBlind=" + bigBlind +
        ", smallBlind=" + smallBlind +
        ", numberOfCardsInDeck=" + numberOfCardsInDeck +
        '}';
  }
}
