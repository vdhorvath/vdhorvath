package Model;

import Controller.Players;
import View.Table;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class PokerHandEval {


  public PokerHandEval() {

  }


  public static List<Card> cardsInPlay(Players player, Table table) {
    List<Card> cardList = new ArrayList<>();

    for (int i = 0; i < player.getHand().size(); i++) {
      cardList.add(player.getHand().get(i));
    }

    for (int j = 0; j < table.size(); j++) {
      cardList.add(table.getBoard().get(j));
    }

    return cardList;

  }

  
  public static Card highCard(Players player, Table table) {
    List<Card> cardList = cardsInPlay(player, table);
    Collections.sort(cardList, Collections.reverseOrder());
    System.out.println(cardList.get(0));
    return cardList.get(0);

  }


}






