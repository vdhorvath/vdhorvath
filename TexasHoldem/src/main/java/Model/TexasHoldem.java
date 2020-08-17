package Model;

import Controller.CurrGamePlayers;
import Controller.Dealer;
import Controller.Players;
import View.Table;
import java.util.ArrayList;
import java.util.List;



public class TexasHoldem {
  private Dealer dealer;
  private CurrGamePlayers currPlayers;
  private Table currTable;
  private DeckOfCards currDeckOfCards;
  private Double gameBigBlind = 1000.0;
  private Double roundBigBlind = 1000.0;
  private Double smallBlind = roundBigBlind / 2;
  private boolean raiseFlag = false;
  private List<Players> foldList = new ArrayList<>();




  public TexasHoldem(Integer numberOfPlayers) {
    this.currDeckOfCards = new DeckOfCards();
    this.currPlayers = new CurrGamePlayers(numberOfPlayers);
    this.currTable = new Table();
    this.dealer = new Dealer(currDeckOfCards, currTable);
    this.roundBigBlind = getRoundBigBlind();


  }

  /**
   * Create a new Game, by calling the create players from current Players.
   */

  public void newGame() {
    this.currPlayers.createPlayersForGame();

  }



  /**
   * Setter function to update the number of players in the game.
   * @param minRoundBet
   */


  public void updateRoundBigBlind(Double minRoundBet) {
    this.roundBigBlind = minRoundBet;

  }

  /**
   * Update the flag
   */

  public void updateFlag() {
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
   * Validate a player's choice
   *
   */


  public void validatePlayersChoice(List<Players> currPlayers, Players player)
      throws NoCashException {

        // if a player folds

    if (player.getChoice() == 3) {
      foldPlayer(currPlayers, player);

      // if a player checks or call

    } else if(player.getChoice() == 1) {
      validatePlayersBet(player, this.roundBigBlind);

      // if a player raises

    } else if (player.getChoice() == 2) {
      validatePlayersBet(player, dealer.askPlayerBetAmt());

    }


  }


  /**
   * Validate a players bet about
   *
   * @return a double, players bet amount.
   */


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

      changeAllPlayersFlagToTrue(player);


      System.out.println("The minimum has increased, the minimum bet is " + "" + this.roundBigBlind);
      return this.currTable.addBetsToPot(amount);
    }
    // take money from players purse
    player.betOrCheck(this.roundBigBlind);

    // update players flag after the blind has been raised

    System.out.println("this should print 1");
    changeAllPlayersFlagToTrue(player);

    // update the pot, adding funds to pot

    return this.currTable.addBetsToPot(amount);
  }


  /**
   * Update all the Players flags
   * @param p players in the game.
   */



  public void changeAllPlayersFlagToTrue(Players p) {
    if (this.raiseFlag == true && this.roundBigBlind > this.gameBigBlind) {
      System.out.println("this should print 2");
      p.updateRaiseFlagTrue();
    }

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
    for (Players player : this.currPlayers.getCurrPlayers()) {
      if (player.getPurse().equals(0.0)) {
        System.out.println(String.format("%s, You Lose!", player.toCustomString()));
        this.currPlayers.remove(player);

      }
    }
  }




  /**
   * One betting round
   * @throws NoCashException
   */


  public void bettingRound() throws NoCashException {
    List<Players> shiftPlayersForBets =
        this.currPlayers.shiftPlayersForBets(this.currPlayers.getCurrPlayers(),
            this.currPlayers.indexOfFirstToAct());

    for (Players player : shiftPlayersForBets) {
      if (!this.foldList.contains(player)) {
        bettingHelper(shiftPlayersForBets, player);
      } else
        // skip player
        shiftPlayersForBets.listIterator().next();

    }

    // If there is a raise we will need to re-loop through all the players
    // that have not raised

    if (this.raiseFlag != false) {
      System.out.println("Game Flag is" + " " + this.raiseFlag);
          for (Players player : shiftPlayersForBets ) {
            if (!this.foldList.contains(player) && player.getRaiseFlag() == false) {
              System.out.println("Print THIS is the HELPER");
              this.bettingHelper(shiftPlayersForBets, player);

            }

        }
          // update game flag
          this.updateFlag();

          // update all player flags for a new round
          this.updatePlayerFlags(shiftPlayersForBets);

          // reset the round big Blind to
          this.roundBigBlind = this.gameBigBlind;
    }

  }


  /**
   * Betting Helper function to help with printing, validate
   * a player's choice
   * @param shiftPlayersForBets
   * @param player
   * @throws NoCashException
   */



  private void bettingHelper(List<Players> shiftPlayersForBets, Players player)
      throws NoCashException {
    System.out.println(player.toString());
    System.out.println(player.toCustomString());
    dealer.askPlayerForChoice(player);
    this.validatePlayersChoice(shiftPlayersForBets, player);
    System.out.println("_________________________________");
    System.out.println(player.toString() + " " + player.getRaiseFlag());
    System.out.println("_________________________________");
    System.out.println("_________________________________");
    System.out.println("This is the current pot" + " " + this.currTable.getPot());
    System.out.println("_________________________________");
  }



  /**
   * Update all the player's flag
   * @param shiftPlayersForBets
   */

  private void updatePlayerFlags(List<Players> shiftPlayersForBets) {
      for(Players p: shiftPlayersForBets) {
        p.updateRaiseFlagFalse();
      }

  }



  public void PlayGame() throws NoCashException {
    int round = 0;

    System.out.println("_____________________________");
    System.out.println("_____________________________");
    System.out.println("_____________________________");

    dealer.shuffleDeck(this.currDeckOfCards);
    dealer.deal(this.currDeckOfCards, this.currPlayers.getCurrPlayers());

    while (round != 1) {
      printLines();
      bettingRound();
      printLines();
      System.out.println("FLOP");
      dealer.dealFlop(this.currDeckOfCards);
      printLines();
      bettingRound();
      printLines();
      System.out.println("TURN");
      printLines();
      dealer.dealTurnOrRiver(this.currDeckOfCards);
      this.bettingRound();
      printLines();
      System.out.println("RIVER");
      dealer.dealTurnOrRiver(this.currDeckOfCards);
      printLines();
      this.bettingRound();
      printLines();

      round++;

    }


  }

  private void printLines() throws NoCashException {
    System.out.println("_____________________________");
    System.out.println("_____________________________");
    System.out.println("_____________________________");
    System.out.println("_____________________________");
    System.out.println("_____________________________");
    System.out.println("_____________________________");
  }

  // Check if only one player has money in the there purse.
    // if only one player has money in their purse game over



}







