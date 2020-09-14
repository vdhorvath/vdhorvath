package Model;

public enum HandRank {

  RoyalFlush(9), StraightFlush(8), FourOfKind(7), Fullhouse(6), Flush(5), Straight(4), ThreeOfKind(3), Pair(2), HighCard(1);

  private int handRank;



  HandRank(int handRank) {
    this.handRank = handRank;
  }



  public int getHandRank() {
    return this.handRank;
  }


}

