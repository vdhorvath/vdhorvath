package Model;

import Controller.Dealer;
import Controller.Players;
import View.Table;
import java.util.ArrayList;

import java.util.List;

public class TexasHoldem {

  private Integer numberOfPlayers;
  private Dealer dealer;
  private List<Players> currPlayers;
  private Table currTable;
  private DeckOfCards currDeckOfCards;
  private Double gameBigBlind = 1000.0;
  private Double roundBigBlind = 1000.0;
  private Double smallBlind = roundBigBlind / 2;
  private boolean raiseFlag = false;
  private List<Players> foldList = new ArrayList<>();




  public TexasHoldem(Integer numberOfPlayers) {
    this.numberOfPlayers = numberOfPlayers;
    this.currDeckOfCards = new DeckOfCards();
    this.currPlayers = new ArrayList<>();
    this.currTable = new Table();
    this.dealer = new Dealer(currDeckOfCards, currTable);
    this.roundBigBlind = getRoundBigBlind();


  }


  /**
   * Get all the current players in the game.
   * @return an Array list of players in the game.
   */


  public List<Players> getCurrPlayers() {
    return this.currPlayers;
  }




  public void newGame() {
    createPlayer();

  }


  /**
   * Create number of players in the game.
   */

  public void createPlayer() {
    for (int i = 0; i < this.numberOfPlayers; i++) {
      Players player = new Players();
      this.currPlayers.add(player);
    }


  }


  /**
   * Setter function to update the number of players in the game.
   * @param minRoundBet
   */


  public void updateRoundBigBlind(Double minRoundBet) {
    this.roundBigBlind = minRoundBet;

  }

  public void updateFlag(boolean raiseFlag) {
    this.raiseFlag = false;
  }


  /**
   * Getter for the
   * @return the big blind for the round.
   */

  public Double getRoundBigBlind() {
    return roundBigBlind;
  }

  /**
   * Validate a players bet about
   *
   * @return a double, players bet amount.
   */


/// Need to split up logic here


  public void validatePlayersChoice(List<Players> currPlayers, Players player)
      throws NoCashException {

    if (player.getChoice() == 3) {
      foldPlayer(currPlayers, player);


    } else if(player.getChoice() == 1) {
      validatePlayersBet(player, this.roundBigBlind);


    } else if (player.getChoice() == 2) {
      validatePlayersBet(player, dealer.askPlayerBetAmt());

    }


  }


  public Double validatePlayersBet(Players player, Double amount) throws NoCashException {
    Double newBet = 0.0;
    if (amount < this.roundBigBlind) {
      System.out
          .println("Sorry your bet was too low, the minimum bet is " + "" + this.roundBigBlind);
      newBet = dealer.askPlayerBetAmt();
      return this.validatePlayersBet(player, newBet);

    } else if (amount > this.roundBigBlind) {
      updateRoundBigBlind(amount);
      // take money from players purse
      player.betOrCheck(amount);

      // flag for raise bet
      this.raiseFlag = true;

      System.out.println("The minimum has increased, the minimum bet is " + "" + this.roundBigBlind);
      return this.currTable.addBetsToPot(amount);
    }
    // take money from players purse
    player.betOrCheck(this.roundBigBlind);
    // update the pot, adding funds to pot
    return this.currTable.addBetsToPot(amount);
  }

  /**
   * Skip Player's Turn if they fold.
   * @param currPlayers Current players in the game
   * @param player player that has folded.
   */



  public void foldPlayer(List<Players> currPlayers, Players player) {
    this.foldList.add(player);
    currPlayers.listIterator().next();
      System.out.println("---------------------------------");
      System.out.println(player.toCustomString() + " " + "has folded");
      System.out.println("---------------------------------");

    }




  public void checkPlayerLosses() {
    for (Players player : this.currPlayers) {
      if (player.getPurse().equals(0.0)) {
        System.out.println(String.format("%s, You Lose!", player.toCustomString()));
        this.currPlayers.remove(player);

      }
    }
  }

  /**
   * Rotate Blinds, move players from position based on Small Blind and Big Blind
   */


  public void rotateBlinds() {
    Players temp = this.getCurrPlayers().get(0);
    this.currPlayers.remove(0);
    this.currPlayers.add(temp);
    System.out.println(this.getCurrPlayers());
  }


  public Integer indexOfFirstToAct() {
    if (this.currPlayers.size() < 3) {
      return 1;
    } else
      return 2;
  }


  public List<Players> shiftPlayersForBets(List<Players> currPlayers, Integer index) {
    Integer i = index;
    Integer j = 0;
    List<Players> shiftedPlayerList = new ArrayList() {
    };

    while (i <= currPlayers.size() - 1) {
      shiftedPlayerList.add(currPlayers.get(i));
      i++;
    }

    while (j < indexOfFirstToAct()) {
      shiftedPlayerList.add(currPlayers.get(j));
      j++;

    }
    return shiftedPlayerList;
  }


  /**
   * One betting round
   * @throws NoCashException
   */


  public void bettingRound() throws NoCashException {
    List<Players> shiftPlayersForBets =
        shiftPlayersForBets(this.currPlayers, indexOfFirstToAct());

      for (Players player : shiftPlayersForBets) {
        if (!this.foldList.contains(player)) {
          System.out.println(player.toString());
          System.out.println(player.toCustomString());
          dealer.askPlayerForChoice(player);
          this.validatePlayersChoice(shiftPlayersForBets, player);
          System.out.println("_________________________________");
          System.out.println("This is the current pot" + " " + this.currTable.getPot());
          System.out.println("_________________________________");
        } else
          shiftPlayersForBets.listIterator().next();
      } if (this.raiseFlag != false) {
        this.updateFlag(true);
        bettingRound();

      }

  }



  public void PlayGame() throws NoCashException {
    int round = 0;

    System.out.println("_____________________________");
    System.out.println("_____________________________");
    System.out.println("_____________________________");

    dealer.shuffleDeck(this.currDeckOfCards);
    dealer.deal(this.currDeckOfCards, this.currPlayers);

    while (round != 1) {

      System.out.println("_____________________________");
      System.out.println("_____________________________");
      System.out.println("_____________________________");
      this.bettingRound();
      System.out.println("_____________________________");
      System.out.println("_____________________________");
      System.out.println("_____________________________");
      System.out.println("FLOP");
      dealer.dealFlop(this.currDeckOfCards);
      System.out.println("_____________________________");
      System.out.println("_____________________________");
      System.out.println("_____________________________");
      this.bettingRound();
      System.out.println("_____________________________");
      System.out.println("_____________________________");
      System.out.println("_____________________________");
      System.out.println("TURN");
      System.out.println("_____________________________");
      System.out.println("_____________________________");
      System.out.println("_____________________________");
      dealer.dealTurnOrRiver(this.currDeckOfCards);
      this.bettingRound();
      System.out.println("_____________________________");
      System.out.println("_____________________________");
      System.out.println("_____________________________");
      System.out.println("RIVER");
      dealer.dealTurnOrRiver(this.currDeckOfCards);
      this.bettingRound();
      System.out.println("_____________________________");
      System.out.println("_____________________________");
      System.out.println("_____________________________");

      round++;

    }


  }

    // Check if only one player has money in the there purse.
    // if only one player has money in their purse game over

}







