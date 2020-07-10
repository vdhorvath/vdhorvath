#include <stdio.h>

#include "a4.h"

void PrintCard(Card *a_card) {
  printf("%s_%s", GetName(a_card->name), GetSuit(a_card->suit));
}

void PrintHand(Hand *a_hand) {
  int i = 0;

  CardNode *cih = a_hand->first_card;

  while (cih != NULL) {
    printf("%d: ", i++);
    PrintCard(cih->this_card);
    printf("\n");

    cih = cih->next_card;
  }
}


// THIS BREAKS THE ABSTRACTION!
// This method is here as a helper to help you debug.
// DO NOT follow this pattern to access cards in the deck elsewhere.
void PrintDeck(Deck *a_deck) {
  int i;

  for (i = 0; i < kNumCardsInDeck; i++) {
    printf("%d: ", i);
    PrintCard(a_deck->cards[i]);
    printf("\n");
  }
}

char *GetSuit(Suit suit) {
  switch (suit) {
    case HEARTS:
      return "Hearts";
    case SPADES:
      return "Spades";
    case CLUBS:
      return "Clubs";
    case DIAMONDS:
      return "Diamonds";

    default:
    return "Unknown Suit";
  }
}

char *GetName(Name name) {
  switch (name) {
    case NINE:
      return "Nine";
    break;
    case TEN:
      return "Ten";
    break;

    case JACK:
      return "Jack";
    break;

    case QUEEN:
      return "Queen";
    break;

    case KING:
      return "King";
    break;

    case ACE:
      return "Ace";
    break;

    default:

    return "Unknown";
  }
}
