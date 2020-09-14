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
  ArrayList<Card> playerHand;
  ArrayList<Card> board;
  Players player1;
  Table table;
  PokerHandEval pokerHandEval;





  @Before
  public void setUp() throws Exception {
    card1 = new Card(Suit.Diamonds, Value.King);
    card2 = new Card(Suit.Hearts, Value.Queen);
    card3 = new Card(Suit.Spades, Value.King);
    card4 = new Card(Suit.Diamonds, Value.Nine);
    card5 = new Card(Suit.Spades, Value.Four);
    card6 = new Card(Suit.Hearts, Value.King);


    playerHand = new ArrayList<>();
    board = new ArrayList<>();


    playerHand.add(card1);
    playerHand.add(card2);


    player1 = new Players(playerHand);


    board.add(card3);
    board.add(card4);
    board.add(card6);

    table = new Table(board);

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
}