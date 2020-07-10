#include <stdio.h>
#include <stdlib.h>

#include "a4.h"

#define PRINT_DEBUG 1

Deck* CreateDeck() {
  Deck* adeck;
  adeck = malloc(sizeof(Deck));
  adeck->top_card = -1;
  return adeck;
}

int IsDeckEmpty(Deck* deck) {
  if (deck->top_card == -1) {
    return 1;
  }
  return 0;
}

Deck* PushCardToDeck(Card* card, Deck* deck) {
  if (deck->top_card < kNumCardsInDeck - 1) {
    deck->top_card++;
    deck->cards[deck->top_card] = card;
    return deck;
  }
  return NULL;
}


Card* PeekAtTopCard(Deck* deck) {
  if (deck->top_card >= 0) {
    return deck->cards[deck->top_card];
  }
  return NULL;
}

Card* PopCardFromDeck(Deck* deck) {
  if (deck->top_card >= 0) {
    Card *card = deck->cards[deck->top_card];
    deck->top_card--;
    return card;
  }
  return 0;
}


Deck* PopulateDeck() {
  int index = 0;
  Deck *deck = CreateDeck();
  Suit  s;
  Name  n;

  for (s = HEARTS; s <= DIAMONDS; s++) {
    for (n = NINE; n <= ACE; n++) {
      deck->cards[index++] = CreateCard(s, n);
    }
    n = NINE;
  }
  deck->top_card = 23;
  return deck;
}

void DestroyDeck(Deck* deck) {
     for (int i = 0; i <= deck->top_card; i++) {
       DestroyCard(deck->cards[i]);
  }
  free(deck);
}

void Shuffle(Deck* deck) {
  for (int j = 0; j < 500; j++) {
    for (int i = 0; i < kNumCardsInDeck; i++) {
      int k = rand() % kNumCardsInDeck;
      Card *temp = deck->cards[i];
      deck->cards[i] = deck->cards[k];
      deck->cards[k] = temp;
    }
  }
}


void Deal(Deck *aDeck, Hand *p1hand, Hand *p2hand) {
  for (int i = 0; i < kNumCardsInHand; i++) {
    Card *addCard1 = PopCardFromDeck(aDeck);
    Card *addCard2 = PopCardFromDeck(aDeck);
    AddCardToHand(addCard1, p1hand);
    AddCardToHand(addCard2, p2hand);
  }
}
