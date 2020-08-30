package Model;

import Controller.Players;
import View.Table;



public class PokerHandEval {

  Players player;
  Table table;


  public PokerHandEval(Players player, Table table) {
    this.player = player;
    this.table = table;
  }


  public static Card highCard(Players player, Table table) {
    int card1 = 0;
    int card2 = 1;

    Card currHighCard = null;

    if (player.getHand().getCards(card1).compareTo(player.getHand().getCards(card2)) == 1) {
      currHighCard = player.getHand().getCards(card1);

    } else if (player.getHand().getCards(card1).compareTo(player.getHand().getCards(card2)) == -1) {
      currHighCard = player.getHand().getCards(card2);
    }

    // This will iterate through the board/table cards to find the highest card

    for (int i = 0; i < table.size(); i++) {
      if (table.getBoard().getCards(i).compareTo(currHighCard) > 0) {
        currHighCard = table.getBoard().getCards(i);
      }

    }
    return currHighCard;
  }




  public static Hand pair(Players player, Table table) {
    int card1 = 0;
    int card2 = 1;

    Hand pair = new Hand();

    // case 1 pair in hand

    if (player.getHand().getCards(card1).compareTo(player.getHand().getCards(card2)) == 0) {
      return player.getHand();
    }

    System.out.println(table.size());


    // case pair in hand and table

    // case 3 pair on table but not in hand


      for (int playerCard = 0; playerCard < player.getHand().size(); playerCard++)
        for (int tableCard = 0; tableCard < table.size(); tableCard++) {

          if (table.getBoard().getCards(card1).compareTo(player.getHand().getCards(playerCard))
              == 0) {
            pair.addCard(player.getHand().getCards(playerCard));
            pair.addCard(table.getBoard().getCards(card1));
          }
          return pair;

        }


    return pairOnTableHelper(table);

  }


  /**
   * Helper Pair method to find a pair on the table only
   * @param table
   * @return ArrayList of Cards
   */


  public static Hand pairOnTableHelper(Table table) {
    Hand pair = new Hand();

    for (int i = 0; i < table.size(); i++) {
      for (int j = 1; j < table.size(); j++) {
        if (table.getBoard().getCards(i).compareTo(table.getBoard().getCards(j)) == 0) {
          pair.addCard(table.getBoard().getCards(i));
          pair.addCard(table.getBoard().getCards(j));
        }
      }
      return pair;

    }
    return null;

  }


}







/*
  public boolean threeOfKind(Table table, Players player) {
    if(this.hasPair(player.getHand())) {
      for(int card = 0; card < table.size(); card++) {
        if(player.getHand().get(0).getValue().equals(player.getHand().get(card).getValue()));
        return true;
      }
    }
    return false;

  }

}
*/
