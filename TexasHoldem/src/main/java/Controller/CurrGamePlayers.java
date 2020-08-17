package Controller;

import java.util.ArrayList;
import java.util.List;

public class CurrGamePlayers {

  private List<Players> currPlayers;
  private Integer numberOfPlayers;



  public CurrGamePlayers(Integer numberOfPlayers) {
    this.currPlayers = new ArrayList<>();
    this.numberOfPlayers = numberOfPlayers;

  }



  /**
   * Index of the first player to act.
   * @return
   */


  public Integer indexOfFirstToAct() {
    if (this.currPlayers.size() < 3) {
      return 1;
    } else
      return 2;
  }




  /**
   * Create number of players in the game.
   */

  public void createPlayersForGame() {
    for (int i = 0; i < this.numberOfPlayers; i++) {
      Players player = new Players();
      this.currPlayers.add(player);
    }
  }



  /**
   * Get all the current players in the game.
   * @return an Array list of players in the game.
   */


  public List<Players> getCurrPlayers() {
    return this.currPlayers;
  }


  /**
   * Add a player to the list
   * @param player
   */



  public void add(Players player) { this.currPlayers.add(player); }


  /**
   * Return the Size of the List of players
   * @return Integer
   */

  public Integer size() {
    return this.numberOfPlayers;
  }


  /**
   * Remove a player from the List of players
   * @param players
   * @return boolean, that the player has been removed.
   */


  public boolean remove(Players players) {
    return this.currPlayers.remove(players);

  }




  /**
   * Rotate Blinds, move players from position based on Small Blind and Big Blind
   */


  public void rotateBlinds() {
    Players temp = this.currPlayers.get(0);
    this.currPlayers.remove(0);
    this.currPlayers.add(temp);
    System.out.println(this.currPlayers);
  }



  /**
   * Shift the players of the game based on the first to Act.
   * @param currPlayers in the game
   * @param index index of the first to act
   * @return List of players
   */


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



}
