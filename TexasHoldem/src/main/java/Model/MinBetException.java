package Model;

public class MinBetException extends Exception {
  public MinBetException() {
    super("Sorry but you bet is too low, please bet $1000.00 or more ");
  }


}
