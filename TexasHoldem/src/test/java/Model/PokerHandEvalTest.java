package Model;


import static org.junit.Assert.assertEquals;

import Controller.Players;
import View.Table;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class PokerHandEvalTest {

  Card card1;
  Card card2;
  Card card3;
  Card card4;
  Card card5;
  Card card6;
  Card card7;
  Card card8;
  Card card9;
  Card card10;

  ArrayList<Card> playerHand;
  ArrayList<Card> board;
  Players player1;
  Table table;
  PokerHandEval pokerHandEval;

  ArrayList<Card> playerHand2;
  ArrayList<Card> board2;
  Players player2;
  Table table2;


  @Before
  public void setUp() throws Exception {
    card1 = new Card(Suit.Diamonds, Value.King);

    card3 = new Card(Suit.Spades, Value.King);
    card4 = new Card(Suit.Diamonds, Value.Nine);
    card5 = new Card(Suit.Spades, Value.Four);

    card7 = new Card(Suit.Diamonds, Value.Ace);
    card6 = new Card(Suit.Hearts, Value.King);
    card2 = new Card(Suit.Hearts, Value.Queen);

    card8 = new Card(Suit.Spades, Value.Jack);
    card9 = new Card(Suit.Diamonds, Value.Ten);
    card10 = new Card(Suit.Diamonds, Value.Queen);

    playerHand = new ArrayList<>();
    board = new ArrayList<>();

    playerHand.add(card1);
    playerHand.add(card2);

    player1 = new Players(playerHand);

    board.add(card3);
    board.add(card4);
    board.add(card6);

    table = new Table(board);

    playerHand2 = new ArrayList<>();
    board2 = new ArrayList<>();

    playerHand2.add(card7);
    playerHand2.add(card6);

    player2 = new Players(playerHand2);

    board2.add(card8);
    board2.add(card9);
    board2.add(card2);
    board2.add(card4);
    board2.add(card5);

    table2 = new Table(board2);

    pokerHandEval = new PokerHandEval();


  }


  @Test
  public void cardsInPlay() {
  }

  @Test
  public void highCard() {

  }

  @Test
  public void pair() {
    List<Card> expected = new ArrayList<>();
    expected.add(card1);
    expected.add(card3);

    assertEquals(expected, pokerHandEval.pair(player1, table));

  }

  @Test
  public void threeOfKind() {
    List<Card> expected = new ArrayList<>();

    expected.add(card1); // king of diamonds
    expected.add(card3); // king of spades
    expected.add(card6); // king of hearts

    assertEquals(expected, pokerHandEval.threeOfKind(player1, table));

  }


  @Test
  public void straight() {

    List<Card> expected = new ArrayList<>();
    expected.add(card7);
    expected.add(card6);
    expected.add(card2);
    expected.add(card8);
    expected.add(card9);

    assertEquals(expected, pokerHandEval.straight(player2, table2));


  }

  @Test
  public void flush() {
    List<Card> cardList = pokerHandEval.cardsInPlay(player2, table2);
    System.out.println(cardList);

    ArrayList<Card> playerHand3 = new ArrayList<>();
    playerHand3.add(card4);
    playerHand3.add(card10);

    Players player3 = new Players(playerHand3);

    List<Card> expected = new ArrayList<>();
    expected.add(card10);
    expected.add(card4);
    expected.add(card7);
    expected.add(card1);
    expected.add(card9);

    ArrayList<Card> table3 = new ArrayList<>();
    table3.add(card7);
    table3.add(card8);
    table3.add(card1);
    table3.add(card3);
    table3.add(card9);

    Table tableTest = new Table(table3);

    assertEquals(expected, pokerHandEval.flush(player3, tableTest));

  }

}