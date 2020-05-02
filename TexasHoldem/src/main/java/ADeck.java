

public abstract class ADeck implements IDeck {


  /**
   * Adds a Card to the Stack.
   *
   * @param card A Card push onto the Stack.
   */


  @Override
  public Deck addCard(Card card) {
    return new Deck(card, this);
  }


  @Override
  public int hashCode() {
    return super.hashCode();
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
