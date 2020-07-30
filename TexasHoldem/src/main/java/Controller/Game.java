package Controller;

import Model.DeckOfCards;
import Model.MinBetException;
import Model.NoCashException;
import View.Table;
import java.util.ArrayList;
import java.util.Scanner;


public class Game {

  private Integer numberOfPlayers;
  private ArrayList<Players> currPlayers;
  private Table currTable;
  private Double currRoundPot = 0.0;
  private Double minBet = 1000.00;
  private DeckOfCards currDeckOfCards;
  private Integer round = 0;


  public Game(Integer numberOfPlayers) {
    this.numberOfPlayers = numberOfPlayers;
    this.currPlayers = new ArrayList<>();
    this.currTable = new Table();
    this.currDeckOfCards = new DeckOfCards();
    this.round = 0;


  }

  public Integer getNumberOfPlayers() {
    return numberOfPlayers;
  }


  public ArrayList<Players> getCurrPlayers() {
    return this.currPlayers;
  }



  public void newGame() {
    createPlayer();

  }


  public void createPlayer() {
    for (int i = 0; i < this.numberOfPlayers; i++) {
      Players player = new Players();
      this.currPlayers.add(player);
    }


  }


  public Double updateMinBet(Double minRoundBet) {
    return this.minBet = minRoundBet;


  }


  public void deal() {
    this.currDeckOfCards.shuffleDeck();
    for (Players p : this.currPlayers) {
      p.addCard(this.currDeckOfCards.popCard());
      p.addCard(this.currDeckOfCards.popCard());
      System.out.println(p);
    }
  }

  public void dealFlop() {
    this.currTable.addCard(this.currDeckOfCards.popCard());
    this.currTable.addCard(this.currDeckOfCards.popCard());
    this.currTable.addCard(this.currDeckOfCards.popCard());
    System.out.println("BELOW IS THE FLOP");
    System.out.println(this.currTable.getHand());

  }

  public void dealTurn() {
    System.out.println("BELOW IS THE TURN");
    this.currTable.addCard(this.currDeckOfCards.popCard());
    System.out.println(this.currTable.getHand());

  }

  public void dealRiver() {
    System.out.println("BELOW IS THE RIVER");
    this.currTable.addCard(this.currDeckOfCards.popCard());
    System.out.println(this.currTable.getHand());

  }

  /**
   * Get players bet amount, and update minimum bet
   * @return a double, players bet amount.
   * @throws MinBetException
   */


  /*public Double getPlayerBetAmount() throws MinBetException {
      Scanner scanner = new Scanner(System.in);
      System.out.println("_____________________________");
      System.out.println("_____________________________");
      System.out.println(String
          .format("How much would you like bet, your bet must be a minimum of %f", this.minBet));
      Double amount = scanner.nextDouble();
      if (amount.equals(this.minBet)) {
        this.currRoundPot += amount;
        return amount;
      } else if (amount > this.minBet) {
        return updateMinBet(amount);
      }
      throw new MinBetException();
    }*/





  public void checkOrRaise() throws MinBetException, NoCashException {
    for (Players players : this.currPlayers) {
      System.out.println(players.toCustomString());
      Scanner scanner = new Scanner(System.in);
      Integer choice = scanner.nextInt();

      switch (choice) {
        case 1:
          players.check();
          break;
        case 2:
          break;
        default:
          players.fold();
      }

      if (choice.equals(1)) {
        players.check();
        //} else
        //playerBetsAmt();

      }

    }
  }



    public void checkPlayerLosses() {
      for (Players player : this.currPlayers) {
        if (player.getPurse().equals(0.0))
          ;
        System.out.println(String.format("%s, You Lose!", player.toCustomString()));
        this.currPlayers.remove(player);
      }


    }


    public void updateLeftOfDealer() {
      if (this.round == 0) {
        return;
      } else
        this.currPlayers.add(this.currPlayers.remove(0));
      System.out.println(this.getCurrPlayers());
    }





  public void PlayGame() throws MinBetException, NoCashException {
    deal();
    int round = 0;
    while (round != 3) {

      System.out.println("_____________________________");
      System.out.println("_____________________________");
      System.out.println("_____________________________");

      for(Players players : this.currPlayers) {
        System.out.println(players.toCustomString());
        players.bet(getPlayerBetAmount());

      }



    }


  }
}





