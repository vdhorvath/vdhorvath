package Model;

import Controller.Dealer;
import Controller.Players;
import View.Table;
import java.util.ArrayList;


public class TexasHoldem {

  private Integer numberOfPlayers;
  private Dealer dealer;
  private ArrayList<Players> currPlayers;
  private Table currTable;
  private DeckOfCards currDeckOfCards;
  private Double gameBigBlind = 1000.0;
  private Double roundBigBlind = 1000.0;
  private Double smallBlind = roundBigBlind / 2;
  private boolean raiseFlag = false;


  public TexasHoldem(Integer numberOfPlayers) {
    this.numberOfPlayers = numberOfPlayers;
    this.currDeckOfCards = new DeckOfCards();
    this.currPlayers = new ArrayList<>();
    this.currTable = new Table();
    this.dealer = new Dealer(currDeckOfCards, currTable);


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


  public void updateRoundBigBlind(Double minRoundBet) {
    this.roundBigBlind = minRoundBet;

  }


  public Double getRoundBigBlind() {
    return roundBigBlind;
  }

  /**
   * Validate a players bet about
   *
   * @return a double, players bet amount.
   */


/// Need to split up logic here


  public Double validatePlayersBet(Players player, Double amount) throws NoCashException {
    Double newBet = 0.0;
    if (amount < this.roundBigBlind) {
      System.out
          .println("Sorry your bet was too low, the minimum bet is " + "" + this.roundBigBlind);
      newBet = dealer.askPlayerBetAmt();
      return this.validatePlayersBet(player, newBet);
    } else if (amount > this.roundBigBlind) {
      updateRoundBigBlind(amount);
      player.bet(amount);
      this.raiseFlag = true;
      System.out.println("The minimum has increased, the minimum bet is " + "" + this.roundBigBlind);
      return this.currTable.addBetsToPot(amount);
    }
    return this.currTable.addBetsToPot(amount);
  }





  public void validateChoice(Players player) throws NoCashException {
    if (player.getChoice() == 1) {
      player.check();


    } else if (player.getChoice() == 2) {
      this.shiftPlayersForBets(this.currPlayers, this.indexOfBigBlind());


    } else if (player.getChoice() == 3) {
      player.fold();

  } else
    System.out
        .println("Sorry please enter a valid number");
    dealer.askPlayerForChoice(player);

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


  public Players smallBlindPlayer() {
    return this.currPlayers.get(0);
  }


  public Integer indexOfBigBlind() {
    if (this.currPlayers.size() < 3) {
      return 0;
    } else
      return 1;
  }


  public Integer indexOfFirstToAct() {
    if (this.currPlayers.size() < 3) {
      return 1;
    } else
      return 2;
  }


  public ArrayList<Players> shiftPlayersForBets(ArrayList<Players> currPlayers, Integer index) {
    Integer i = index;
    Integer j = 0;
    ArrayList<Players> shiftedPlayerList = new ArrayList<>();

    while (i <= currPlayers.size() - 1) {
      shiftedPlayerList.add(currPlayers.get(i));
      i++;
    }

    while (j <= indexOfFirstToAct()) {
      shiftedPlayerList.add(currPlayers.get(j));
      j++;

    }
    return shiftedPlayerList;
  }


  public void bettingRound() throws NoCashException { // may get rid of  {
    Integer firstToAct = 0;
    ArrayList<Players> shiftPlayersForBets =
        shiftPlayersForBets(this.currPlayers, indexOfFirstToAct());

    for(Players p : shiftPlayersForBets) {
      System.out.println(p.toCustomString());
      dealer.askPlayerForChoice(p);
      this.validateChoice(p);
      this.validatePlayersBet(p, dealer.askPlayerBetAmt());
      System.out.println("This is the current pot" + " " + this.currTable.getPot());
    }

    if (this.raiseFlag != false && shiftPlayersForBets.get(firstToAct).getChoice() != 1) {
      bettingRound();
    } else
      dealer.askBigBlindCheckOrBet(shiftPlayersForBets);

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

      dealer.dealFlop(this.currDeckOfCards);
      System.out.println("Size of deck after flop");
      System.out.println(this.currDeckOfCards.size());
      dealer.dealTurnOrRiver(this.currDeckOfCards);
      System.out.println("Size of deck after Turn ");
      System.out.println(this.currDeckOfCards.size());
      dealer.dealTurnOrRiver(this.currDeckOfCards);
      System.out.println(this.currDeckOfCards.size());

      round++;

    }

    // Check if only one player has money in the there purse.
    // if only one player has money in their purse game over

    round--;

    //this.bettingRound();
    //askBigBlindCheckOrBet();
    //validateCheckOrRaiseBeforeCards(this.bigBlindPlayer().getChoice());
    //System.out.println(this.bigBlindPlayer().getChoice());

  }
}






