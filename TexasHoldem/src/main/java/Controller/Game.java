package Controller;

import Model.NoCashException;
import View.Table;
import java.util.ArrayList;
import java.util.Scanner;


public class Game {
  private Integer numberOfPlayers;
  private ArrayList<Players> currPlayers;
  private Table currTable;



  public Game(Integer numberOfPlayers) {
    this.numberOfPlayers = numberOfPlayers;
    this.currPlayers = new ArrayList<>();
    this.currTable = new Table();


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


  public void deal() {
    Dealer dealer = new Dealer();
    for (Players p : this.currPlayers) {
      p.addCard(this.currTable.getCurrDeckOfCards().popCard());
      p.addCard(this.currTable.getCurrDeckOfCards().popCard());
      System.out.println(p);
    }
    dealer.addCard(this.currTable.getCurrDeckOfCards().popCard());
    dealer.addCard(this.currTable.getCurrDeckOfCards().popCard());
    System.out.println(dealer);
  }

  public void dealFlop() {
    this.currTable.addCard(this.currTable.getCurrDeckOfCards().popCard());
    this.currTable.addCard(this.currTable.getCurrDeckOfCards().popCard());
    this.currTable.addCard(this.currTable.getCurrDeckOfCards().popCard());
  }

  public void dealTurn() {
    this.currTable.addCard(this.currTable.getCurrDeckOfCards().popCard());

  }

  public void dealRiver() {
    this.currTable.addCard(this.currTable.getCurrDeckOfCards().popCard());

  }


  public Double playerBets() throws NoCashException {
    for (Players players : this.getCurrPlayers()) {
      Scanner scanner = new Scanner(System.in);
      System.out.println("_____________________________");
      System.out.println(players.toCustomString());
      System.out.println("How much would you like bet?");
      Double amount = scanner.nextDouble();
      return amount;

    }
    throw new NoCashException();
  }


  public void PlayGame() throws NoCashException {
    deal();
    int round = 0;
    while(round != 1) {
      System.out.println("_____________________________");
      System.out.println("_____________________________");
      System.out.println("_____________________________");
      playerBets();
      dealFlop();
      round++;

    }



  }




}