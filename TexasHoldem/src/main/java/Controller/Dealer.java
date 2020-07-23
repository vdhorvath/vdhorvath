package Controller;

import Model.Card;
import java.util.ArrayList;


public class Dealer {
  private ArrayList<Card> dealerHand;


  public Dealer(ArrayList<Card> dealerHand) {
    this.dealerHand = dealerHand;

  }

  public ArrayList<Card> getDealerHand() {
    return dealerHand;
  }

  // Possible come back and add a flag



}



