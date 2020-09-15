package View;
import Model.Card;
import java.util.ArrayList;


public class Table {
  private Double pot = 0.0;
  private ArrayList<Card> board;


  /**
   * Constructor for the Table class
   */

  public Table(ArrayList<Card> board) {
    this.pot = pot;
    this.board = board;

  }



  public Table() {
    this.pot = pot;
    this.board = new ArrayList<>();

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
    if(this.getBoard() == null) {
      return 0;
    }
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




  public void addCard(Card card) {
    this.board.add(card);
  }


  /**
   * Get the board cards
   * @return Hand, the current cards that are on the board.
   */


  public ArrayList<Card> getBoard() {
    return this.board;
  }


  public void printBoard() {
    if (!this.getBoard().isEmpty()) {
      System.out.println(this.getBoard());
    }
  }



  @Override
  public String toString() {
    return "Table{" +
        "pot=" + pot +
        ", board=" + board +
        '}';
  }
}
