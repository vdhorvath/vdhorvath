import View.Game;
import java.util.Scanner;

public class Main {


  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);  // Create a Scanner object
    System.out.println("Enter number of players");
    Integer numberOfPlayers = scanner.nextInt();


    Game game = new Game(numberOfPlayers);

    game.newGame();
    game.deal();















  }




}



















