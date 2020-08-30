package View;
import Model.Hand;



public class Table {
  private Double pot = 0.0;
  private Hand board;



  /**
   * Constructor for the Table class
   */

  public Table(Hand board) {
    this.pot = pot;
    this.board = board;


  }




  /**
   * Gets the Pot amount
   * @return Double, Pot amount.
   */


  public Double getPot() {
    return pot;
  }


  /**
   * Gets the size of the current cards on the table.
   * @return Integer of the Table
   */

  public Integer size() {

    return this.board.size();
  }


  /**
   * Adds additional bets to the pot.
   * @param pot
   * @return Double, total number of bets for a round.
   */

  public Double addBetsToPot(Double pot) {
    return this.pot += pot;
  }








  /**
   * Get the board cards
   * @return Hand, the current cards that are on the board.
   */



  public Hand getBoard() {
    return this.board;
  }


  @Override
  public String toString() {
    return "Table{" +
        "pot=" + pot +
        ", board=" + board +
        '}';
  }
}
