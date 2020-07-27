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




  public Game(Integer numberOfPlayers) {
    this.numberOfPlayers = numberOfPlayers;
    this.currPlayers = new ArrayList<>();
    this.currTable = new Table();
    this.currDeckOfCards = new DeckOfCards();



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


  public Double playerBetsAmt() throws MinBetException {
    Scanner scanner = new Scanner(System.in);
    System.out.println(String.format("How much would you like bet, your bet must be a minimum of %f",this.minBet));
    Double amount = scanner.nextDouble();
    if (amount.equals(this.minBet)) {
      this.currRoundPot += amount;
      return amount;
    } else if (amount > this.minBet) {
      return updateMinBet(amount);
    }
    throw new MinBetException();
  }



  public void choicesCheckRaise() throws MinBetException {
    System.out.println("_____________________________");
    System.out.println("Player 1 Check or Re-raise?");
    System.out.println("For Check enter = 1 OR Raise enter = 2");
    System.out.println("_______________________");
    System.out.println("_______________________");
    System.out.println("_______________________");
    Players players = new Players();
    Scanner scanner = new Scanner(System.in);
    Integer choice = scanner.nextInt();


    switch(choice) {
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
    } else playerBetsAmt();


  }


  public void checkPlayerLosses() {
    for (Players player : this.currPlayers) {
      if (player.getPurse().equals(0.0));
      this.currPlayers.remove(player);
      System.out.println("You Lose!");
    }


  }




  public void PlayGame() throws NoCashException, MinBetException {
    deal();
    int round = 0;
    while (round != 1) {

      System.out.println("_____________________________");
      System.out.println("_____________________________");
      System.out.println("_____________________________");

      for (Players players : this.currPlayers) {
        System.out.println("_____________________________");
        System.out.println(players.toCustomString());
        players.bet(playerBetsAmt());

      }
      choicesCheckRaise();

      dealFlop();

      System.out.println(this.currRoundPot);
      System.out.println(this.currDeckOfCards.size());

      choicesCheckRaise();
      dealTurn();

      System.out.println(this.currRoundPot);
      System.out.println(this.currDeckOfCards.size());

      choicesCheckRaise();
      dealRiver();

      System.out.println(this.currRoundPot);
      System.out.println(this.currDeckOfCards.size());
      checkPlayerLosses();
      round++;

      }


    }
  }




