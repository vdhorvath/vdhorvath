package Model;

public class NoCashException extends Exception {
  public NoCashException() {
    super("Sorry you don't have enough funds to make this bet");
  }

}
