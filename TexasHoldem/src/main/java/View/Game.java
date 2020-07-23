package View;


import Controller.Players;
import Model.DeckOfCards;
import java.util.ArrayList;


public class Game {

  private Integer numberOfPlayers;
  private ArrayList<Players> currGameBoard;



  public Game(Integer numberOfPlayers) {
    this.numberOfPlayers = numberOfPlayers;
    this.currGameBoard = new ArrayList<>();


  }

  public Integer getNumberOfPlayers() {
    return numberOfPlayers;
  }

  public ArrayList<Players> getCurrentBoardGame() {
    return this.currGameBoard;
  }


  public void newGame() {
    createPlayer();

  }


  public void createPlayer() {
    for (int i = 0; i < this.numberOfPlayers; i++) {
      Players player = new Players();
      this.currGameBoard.add(player);
    }
    System.out.println(currGameBoard.size());

  }

  public void deal() {
    DeckOfCards newDeck = new DeckOfCards();
    newDeck.populateDeck();
    newDeck.shuffleDeck();
    for (Players p : this.currGameBoard) {
      p.addCard(newDeck.popCard());
      p.addCard(newDeck.popCard());
    }
    // System.out.println(this.currGameBoard.size());
    // System.out.println(this.currGameBoard);


  }


}