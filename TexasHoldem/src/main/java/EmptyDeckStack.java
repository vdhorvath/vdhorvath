import java.util.EmptyStackException;

public class EmptyDeckStack ADeck {

  public EmptyDeckStack () {

  }

  private static final Integer HASH_CODE = 47;

  /**
   * Tests if this Stack is empty.
   *
   * @return true if and only if the Stack contains no items. Otherwise return false.
   */


  @Override
  public boolean isEmpty() {
    return true;
  }


  /**
   * Returns the most recently-added item.
   *
   * @return The most recently-added item. (Show)
   * @throws EmptyStackException Throws an EmptyStackException if the method is called on an empty
   *                             Stack. Note: EmptyStackException is a built-in Java exception.
   */


  @Override
  public Card showCard() throws EmptyStackException {
    return null;
  }




  @Override
  public int hashCode() {
    return EmptyDeckStack.HASH_CODE;
  }



  @Override
  public boolean equals(Object obj) {
    return super.equals(obj);
  }



  @Override
  public String toString() {
    return super.toString();
  }


}

