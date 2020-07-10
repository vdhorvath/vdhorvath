import java.util.Set;
import java.util.Stack;

public class Main {


  public static void main(String[] args) {

    //Deck deck1 = new Deck();
    //deck1.shuffleDeck(deck1);
    //System.out.println(deck1.size());


    int[] intArray = {1,2,5,9};
    int total = 0;
    int l = intArray.length;
    Stack<Integer> stack = new Stack<Integer>();

      for (int i = 0; l > 0; i++) {
        total += intArray[i];
        l--;
      }
      stack.push(total);


    }


  }




















