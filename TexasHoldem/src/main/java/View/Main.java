package View;


import Model.NoCashException;
import Model.TexasHoldem;
import java.util.Scanner;



public class Main {

  public static void main(String[] args) throws NoCashException {
    System.out.println("_____________________________");
    System.out.println("_____________________________");
    System.out.println("_____________________________");
    System.out.println("WELCOME TO TEXAS HOLDEM!!!");
    System.out.println("_____________________________");
    System.out.println("_____________________________");
    System.out.println("_____________________________");

    Scanner scanner = new Scanner(System.in);  // Create a Scanner object
    System.out.println("Enter number of players");
    Integer numberOfPlayers = scanner.nextInt();

    TexasHoldem texasHoldem = new TexasHoldem(numberOfPlayers);
    texasHoldem.PlayGame();



  }


}















