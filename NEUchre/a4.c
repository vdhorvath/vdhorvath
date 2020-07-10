// Valerie Horvath 2/12/20

#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <string.h>

#include "a4.h"

#define kPrintDebug 1

int SameSuitCheck(Hand *hand, Card *lead_card);
CardNode* CreateCardNode(Card *card, CardNode *prev, CardNode *next);

// Implement the Hand and other functions in here.

// Card function //

Card* CreateCard(Suit suit, Name name) {
  Card *oneCard;
  oneCard = malloc(sizeof(Card));
  oneCard->name = name;
  oneCard->suit = suit;
  return oneCard;
}

void DestroyCard(Card* card) {
  free(card);
}

Hand* CreateHand() {
  Hand *hand = malloc(sizeof(Hand));
  hand->num_cards_in_hand = 0;
  hand->first_card = NULL;
  return hand;
}

/**
 * Helper function for Add card to hand. Create card node
 * ptr.
 */

CardNode* CreateCardNode(Card *card, CardNode *prev, CardNode *next) {
  CardNode *cardNode = malloc(sizeof(CardNode));
  cardNode->this_card = card;
  cardNode->prev_card = prev;
  cardNode->next_card = next;
  return cardNode;
}


void AddCardToHand(Card *card, Hand *hand) {
  if (hand->num_cards_in_hand < kNumCardsInHand) {
    	CardNode *cardNode = CreateCardNode(card, NULL, NULL);
    if (hand->first_card == NULL) {
       hand->first_card = cardNode;
      } else {
        hand->first_card->prev_card = cardNode;
        cardNode->next_card = hand->first_card;
        cardNode->next_card = hand->first_card;
        hand->first_card = cardNode;
      }
      hand->num_cards_in_hand++;
  }
}

Card* RemoveCardFromHand(Card *card, Hand *hand) {
  if (hand->num_cards_in_hand == 0) {
     return NULL;
  } else {
    CardNode *headNode = hand->first_card;
    if (headNode->this_card == card) {
      hand->first_card = headNode->next_card;
      if (hand->first_card != NULL) {
        hand->first_card->prev_card = NULL;
      }
    } else {
      while (headNode->next_card != NULL) {
       if (headNode->this_card == card) {
        headNode->prev_card->next_card = headNode->next_card;
        headNode->next_card->prev_card = headNode->prev_card;
	break;
        }
        headNode = headNode->next_card;
      }
      if (headNode->next_card == NULL && headNode->this_card == card) {
        headNode->prev_card->next_card = NULL;
      }
    }
    hand->num_cards_in_hand--;
    free(headNode);
    return card;
  }
  return NULL;
}


int IsHandEmpty(Hand* hand) {
  if (hand->first_card == NULL) {
    return 1;
  }
  return 0;
}


void DestroyHand(Hand *hand) {
  free(hand);
}

/**
 * Helper function for isLegalmove
 */

int SameSuitCheck(Hand *hand, Card *lead_card) {
  for (int i = 0; i <hand->num_cards_in_hand; i++) {
    hand->first_card->this_card[i];
    if (hand->first_card->this_card->suit == lead_card->suit) {
      return 1;
    } else {
       return 0;
    }
  }
}


int IsLegalMove(Hand *hand, Card *lead_card, Card *played_card) {
  if (SameSuitCheck(hand, lead_card) == 1 &&
    lead_card->suit == played_card->suit) {
       return 1;
  } else if (SameSuitCheck(hand, lead_card) == 0) {
       return 1;
  } return 0;
}



int WhoWon(Card *lead_card, Card *followed_card, Suit trump) {
  if (lead_card->suit == followed_card->suit) {
    return lead_card->name > followed_card->name;
  }
  if (followed_card->suit == trump && lead_card->suit != followed_card->suit) {
     return 0;
  } else {
      return 1;
  }
}
