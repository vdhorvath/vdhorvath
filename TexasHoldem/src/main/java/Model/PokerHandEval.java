package Model;

import Controller.Players;
import View.Table;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



public class PokerHandEval {


  public PokerHandEval() {

  }

  /**
   * Creates a list of cards that current in play.
   *
   * @param player - to get the cards from the players hand.
   * @param table  - to get the cards from the current table.
   * @return
   */


  public List<Card> cardsInPlay(Players player, Table table) {
    List<Card> cardList = new ArrayList<>();

    for (int i = 0; i < player.getHand().size(); i++) {
      cardList.add(player.getHand().get(i));
    }

    for (int j = 0; j < table.size(); j++) {
      cardList.add(table.getBoard().get(j));
    }

    Collections.sort(cardList, Collections.reverseOrder());
    return cardList;

  }

  /**
   * Finds a HighCard in a hand. Default hand if no cards find a hand.
   *
   * @param player
   * @param table
   * @return
   */


  public Card highCard(Players player, Table table) {
    List<Card> cardList = cardsInPlay(player, table);
    System.out.println(cardList.get(0));
    return cardList.get(0);

  }

  /**
   * Find a pair within a Hand.
   *
   * @param player - get a player's hand
   * @param table  - get a table's hand
   * @return pair
   */


  public List<Card> pair(Players player, Table table) {
    List<Card> cardList = cardsInPlay(player, table);
    List<Card> result = new ArrayList<>();

    for (int i = 0; i < cardList.size() - 1; i++) {
      Card one = cardList.get(i);
      Card two = cardList.get(i + 1);

      if (one.getValue().getValue() == two.getValue().getValue() && !result.contains(one)) {
        result.add(one);
        result.add(two);
      }
    }

    System.out.println(result);
    return result;

  }


  public List<Card> threeOfKind(Players player, Table table) {
    List<Card> pairToThree = pair(player, table);
    List<Card> cardsInPlay = cardsInPlay(player, table);
    int card = 0;

    while(card < cardsInPlay.size()) {
      Card currCard = cardsInPlay.get(card);
      if(currCard.getValue().getValue() == pairToThree.get(1).getValue().getValue() &&
          !pairToThree.contains(currCard)) {
        pairToThree.add(currCard);
        return pairToThree;
      }
      card++;

    }
    return null;
  }
}

