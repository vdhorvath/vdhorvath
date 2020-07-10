#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "a4.h"


#define assert(EXPRESSION) (                            \
    (EXPRESSION) ?                                      \
    _assert_pass(#EXPRESSION, __FILE__, __LINE__) :     \
    _assert_fail(#EXPRESSION, __FILE__, __LINE__))

void _assert_fail(const char* expression,
                  const char* file,
                  int line) {
  fprintf(stderr,
          "\n**** Assertion '%s' FAILED, file '%s' line '%d' *******.\n",
          expression,
          file,
          line);
}

void _assert_pass(const char *expression,
                  const char *file,
                  int line) {
  fprintf(stdout,
          "Assertion '%s' passed, file '%s' line '%d'.\n",
          expression,
          file,
          line);
}

void StartTest(const char *test) {
  static int count = 1;
  printf("Test #%d: %s\n\n", count++, test);
}

void EndTest() {
  printf("\n--------------------------------\n\n");
}

void TestCreateDeck() {
  StartTest("create_deck");

  Deck *deck = CreateDeck();

  // deck should be empty at this stage
  assert(deck->top_card < 1);

  // remember that destroy_deck should
  // free all cards present in the
  // deck as well!
  // at this point in the test, the
  // deck should have no cards in it
  // which means this should be
  // equivalent to free(deck).
  DestroyDeck(deck);

  EndTest();
}

void TestPushCardToDeck() {
  StartTest("push_card_to_deck");

  Deck *deck = CreateDeck();

  Card card1 = {NINE, HEARTS, -1};
  int top = deck->top_card;

  PushCardToDeck(&card1, deck);
  top += 1;
  assert(deck->cards[0] == &card1);
  // The index pointing at the top card should increment by 1
  assert(deck->top_card == top);

  Card card2 = {JACK, CLUBS, -1};

  PushCardToDeck(&card2, deck);
  top += 1;
  assert(deck->cards[0] == &card1);
  assert(deck->cards[1] == &card2);
  assert(deck->top_card == top);

  Card card3 = {ACE, SPADES, -1};

  int i = 2;
  for (; i < kNumCardsInDeck; i++) {
    PushCardToDeck(&card3, deck);
    top += 1;
    assert(deck->top_card == top);
  }

  for (i = 2; i < kNumCardsInDeck; i++) {
    assert(deck->cards[i] == &card3);
  }

  Card card4 = {QUEEN, DIAMONDS, -1};
  PushCardToDeck(&card4, deck);

  assert(deck->cards[0] == &card1);
  assert(deck->cards[1] == &card2);
  assert(deck->cards[kNumCardsInDeck - 1] == &card3);

  // not calling DestroyDeck here because cards are on stack.
  // I'm just calling free instead.
  free(deck);

  EndTest();
}

void TestPopulateDeck() {
  StartTest("populate_deck");

  int i = 0;
  Suit s = HEARTS;
  Name n = NINE;

  Deck *deck = PopulateDeck();

  // deck should be populated in order
  // and not shuffled at this stage
  // However, this test does not test
  // for shuffled or not.
  for (; s <= DIAMONDS; s++) {
    for (n = NINE; n <= ACE; n++) {
      int found_card = 0;
      // Not efficient, but doesn't force a deck to be
      //  populated in a specific order.
      for (int i = 0; i < kNumCardsInDeck; i++) {
        if ((deck->cards[i]->suit == s) &&
            (deck->cards[i]->name == n)) {
          found_card++;
        }
      }
      assert(found_card == 1);
    }
  }

  // just to reiterate...
  // I expect your destroy deck function will
  // destroy the cards within it too.
  // if not, then there's a memory leak!
  DestroyDeck(deck);

  EndTest();
}

void TestPeekAtTopCard() {
  StartTest("peek_at_top_deck");

  Deck *emptyDeck = CreateDeck();

  // empty deck peek should return NULL
  assert(PeekAtTopCard(emptyDeck) == NULL);
  free(emptyDeck);

  Deck *deck = PopulateDeck();

  Card last_card = {ACE, DIAMONDS, -1};

  assert(PeekAtTopCard(deck)->name == last_card.name);
  assert(PeekAtTopCard(deck)->suit == last_card.suit);

  DestroyDeck(deck);

  deck = CreateDeck();
  PushCardToDeck(&last_card, deck);

  assert(PeekAtTopCard(deck)->name == last_card.name);
  assert(PeekAtTopCard(deck)->suit == last_card.suit);

  Card another_card = {TEN, CLUBS, -1};
  PushCardToDeck(&another_card, deck);

  assert(PeekAtTopCard(deck)->name == another_card.name);
  assert(PeekAtTopCard(deck)->suit == another_card.suit);

  Card one_more_card = {KING, HEARTS, -1};

  int i = 2;
  for (; i < kNumCardsInDeck; i++) {
    PushCardToDeck(&one_more_card, deck);
    assert(PeekAtTopCard(deck)->name == one_more_card.name);
    assert(PeekAtTopCard(deck)->suit == one_more_card.suit);
  }


  // this card should not be pushed since deck is full at this point
  PushCardToDeck(&last_card, deck);
  // peek should show me the last card that was pushed before
  // deck became full
  assert(PeekAtTopCard(deck)->name == one_more_card.name);
  assert(PeekAtTopCard(deck)->suit == one_more_card.suit);

  free(deck);

  EndTest();
}

void TestPopCardFromDeck() {
  StartTest("pop_card_from_deck");

  Deck *deck = CreateDeck();

  // pop() from empty deck should return NULL
  assert(PopCardFromDeck(deck) == NULL);

  Card card1 = {QUEEN, DIAMONDS, -1};
  PushCardToDeck(&card1, deck);

  assert(PopCardFromDeck(deck) == &card1);

  Card card2 = {TEN, CLUBS, -1};
  PushCardToDeck(&card1, deck);
  PushCardToDeck(&card2, deck);

  assert(PopCardFromDeck(deck) == &card2);

  assert(PopCardFromDeck(deck) == &card1);

  free(deck);

  EndTest();
}

void TestIsDeckEmpty() {
  StartTest("is_deck_empty");

  Deck *deck = CreateDeck();
  assert(IsDeckEmpty(deck));

  Card card1 = {QUEEN, SPADES, -1};

  PushCardToDeck(&card1, deck);
  assert(!IsDeckEmpty(deck));

  PushCardToDeck(&card1, deck);
  assert(!IsDeckEmpty(deck));

  PopCardFromDeck(deck);
  assert(!IsDeckEmpty(deck));

  PopCardFromDeck(deck);
  assert(IsDeckEmpty(deck));

  DestroyDeck(deck);

  EndTest();
}

void TestShuffle() {
  StartTest("test_shuffle");

  int i = 0;
  Suit s = HEARTS;
  Name n = NINE;

  Deck *deck = PopulateDeck();

  int orig_order[kNumCardsInDeck] = {0};

  // deck should be populated in order
  // and not shuffled at this stage
  // However, this test does not test
  // for shuffled or not.
  int index = 0;
  for (; s <= DIAMONDS; s++) {
    for (n = NINE; n <= ACE; n++) {
      int found_card = 0;
      // Not efficient, but doesn't force a deck to be
      //  populated in a specific order.
      for (int i = 0; i < kNumCardsInDeck; i++) {
        if ((deck->cards[i]->suit == s) &&
            (deck->cards[i]->name == n)) {
          found_card++;
          orig_order[index++] = i;
        }
      }
      assert(found_card == 1);
    }
  }

  Shuffle(deck);
  index = 0;

  int num_collisions = 0;
  for (s = HEARTS; s <= DIAMONDS; s++) {
    for (n = NINE; n <= ACE; n++) {
      int found_card = 0;
      // Not efficient, but doesn't force a deck to be
      //  populated in a specific order.
      for (int i = 0; i < kNumCardsInDeck; i++) {
        if ((deck->cards[i]->suit == s) &&
            (deck->cards[i]->name == n)) {
          found_card++;
          // It's okay to fail this assertion a couple times.
          assert(orig_order[index] != i);
          if (orig_order[index] == i) {
            num_collisions++;
          }
          index++;
        }
      }
      assert(found_card == 1);
    }
  }
  // Magic Number:
  // Arbitrary buffer for allowing a card to stay
  // in the same position even though the deck
  // gets shuffled.
  // If you fail this assert, make a note on Piazza.
  assert(num_collisions <= 3);
  DestroyDeck(deck);
  EndTest();
}

void TestCreateHand() {
  StartTest("CreateHand");

  Hand *hand = CreateHand();
  assert(hand != NULL);
  assert(hand->num_cards_in_hand == 0);
  assert(hand->first_card == NULL);

  DestroyHand(hand);
  EndTest();
}

void TestAddCardToHand() {
  StartTest("add_card_to_hand");

  Hand hand;
  memset(&hand, 0, sizeof(Hand));

  Card card1 = {NINE, HEARTS, -1};
  AddCardToHand(&card1, &hand);

  assert(hand.num_cards_in_hand == 1);
  assert(hand.first_card->this_card == &card1);
  assert(hand.first_card->next_card == NULL);
  assert(hand.first_card->prev_card == NULL);

  Card card2 = {TEN, CLUBS, -1};
  AddCardToHand(&card2, &hand);

  assert(hand.num_cards_in_hand == 2);
  assert(hand.first_card->next_card->this_card == &card1);
  assert(hand.first_card->next_card->prev_card->this_card == &card2);
  assert(hand.first_card->next_card->next_card == NULL);
  assert(hand.first_card->this_card == &card2);
  assert(hand.first_card->prev_card == NULL);

  free(hand.first_card->next_card);
  free(hand.first_card);

  EndTest();
}

void TestRemoveCardFromHand() {
  StartTest("remove_card_from_hand");

  Hand hand;
  memset(&hand, 0, sizeof(Hand));

  Card card1 = {NINE, HEARTS, -1};
  Card card2 = {TEN, CLUBS, -1};
  Card card3 = {JACK, SPADES, -1};

  AddCardToHand(&card1, &hand);
  // remove only card
  RemoveCardFromHand(&card1, &hand);

  assert(hand.num_cards_in_hand == 0);
  assert(hand.first_card == NULL);

  AddCardToHand(&card1, &hand);
  AddCardToHand(&card2, &hand);
  // remove last card
  RemoveCardFromHand(&card1, &hand);

  assert(hand.num_cards_in_hand == 1);
  assert(hand.first_card->this_card == &card2);
  assert(hand.first_card->next_card == NULL);
  assert(hand.first_card->prev_card == NULL);

  AddCardToHand(&card1, &hand);
  // remove first card
  RemoveCardFromHand(&card1, &hand);

  assert(hand.num_cards_in_hand == 1);
  assert(hand.first_card->this_card == &card2);
  assert(hand.first_card->next_card == NULL);
  assert(hand.first_card->prev_card == NULL);

  RemoveCardFromHand(&card2, &hand);
  assert(hand.num_cards_in_hand == 0);
  assert(hand.first_card == NULL);

  AddCardToHand(&card1, &hand);
  AddCardToHand(&card2, &hand);
  AddCardToHand(&card3, &hand);
  // remove middle card
  RemoveCardFromHand(&card2, &hand);

  assert(hand.first_card->next_card->this_card == &card1);
  assert(hand.first_card->next_card->prev_card->this_card == &card3);
  assert(hand.first_card->next_card->next_card == NULL);
  assert(hand.first_card->this_card == &card3);
  assert(hand.first_card->prev_card == NULL);

  RemoveCardFromHand(&card3, &hand);
  RemoveCardFromHand(&card1, &hand);

  EndTest();
}

void TestDeal() {
  StartTest("deal");
  // TODO(ahs): Make this test more robust
  Deck *deck = PopulateDeck();
  Shuffle(deck);

  // Hands should get the top 10 cards in the stack.

  Card *expected_hand1[kNumCardsInHand] = {
    deck->cards[kNumCardsInDeck - 1],
    deck->cards[kNumCardsInDeck - 3],
    deck->cards[kNumCardsInDeck - 5],
    deck->cards[kNumCardsInDeck - 7],
    deck->cards[kNumCardsInDeck - 9]
  };

  Card *expected_hand2[kNumCardsInHand] = {
    deck->cards[kNumCardsInDeck - 2],
    deck->cards[kNumCardsInDeck - 4],
    deck->cards[kNumCardsInDeck - 6],
    deck->cards[kNumCardsInDeck - 8],
    deck->cards[kNumCardsInDeck - 10]
  };

  Hand hand1;
  memset(&hand1, 0, sizeof(Hand));

  Hand hand2;
  memset(&hand2, 0, sizeof(Hand));

  Deal(deck, &hand1, &hand2);

  CardNode *ptr = hand1.first_card;

  int i = kNumCardsInHand - 1;
  int num_matches = 0;
  // Go through each card in the hand and
  //   check to see if it exists in the expected hand.
  while (ptr != NULL) {
    // Not efficient, but more robust and non-implementation specific
    for (int i = 0; i < kNumCardsInHand; i++) {
      if ((expected_hand1[i] != NULL) &&
          (ptr->this_card == expected_hand1[i])) {
        expected_hand1[i] = NULL;
        num_matches++;
        printf("got a match!!\n");
        break;
      }
    }

    //    assert(ptr->this_card == expected_hand1[i]);
    ptr = ptr->next_card;
    // i--;
  }
  assert(num_matches == kNumCardsInHand);

  ptr = hand2.first_card;
  i = 4;

  while (ptr != NULL) {
    assert(ptr->this_card == expected_hand2[i]);
    ptr = ptr->next_card;
    i--;
  }

  ptr = hand1.first_card;
  while (ptr != NULL) {
    CardNode *next = ptr->next_card;
    free(ptr->this_card);
    free(ptr);
    ptr = next;
  }

  ptr = hand2.first_card;
  while (ptr != NULL) {
    CardNode *next = ptr->next_card;
    free(ptr->this_card);
    free(ptr);
    ptr = next;
  }

  EndTest();
  DestroyDeck(deck);
  }

void TestIsLegalMove() {
  StartTest("is_legal_move");

  Hand hand;
  memset(&hand, 0, sizeof(Hand));

  Card queen_hearts = {QUEEN, HEARTS, -1};
  Card king_spades = {KING, SPADES, -1};
  Card nine_clubs = {NINE, CLUBS, -1};
  Card nine_hearts = {NINE, HEARTS, -1};

  AddCardToHand(&queen_hearts, &hand);
  AddCardToHand(&king_spades, &hand);
  AddCardToHand(&nine_clubs, &hand);
  AddCardToHand(&nine_hearts, &hand);

  Card ten_hearts = {TEN, HEARTS, -1};

  // Given hand, card led, card played
  assert(IsLegalMove(&hand, &ten_hearts, &queen_hearts));
  assert(!IsLegalMove(&hand, &ten_hearts, &king_spades));
  assert(!IsLegalMove(&hand, &ten_hearts, &nine_clubs));
  assert(IsLegalMove(&hand, &ten_hearts, &nine_hearts));

  Card led_jack_diamonds = {JACK, DIAMONDS, -1};

  assert(IsLegalMove(&hand, &led_jack_diamonds, &queen_hearts));
  assert(IsLegalMove(&hand, &led_jack_diamonds, &king_spades));
  assert(IsLegalMove(&hand, &led_jack_diamonds, &nine_clubs));
  assert(IsLegalMove(&hand, &led_jack_diamonds, &nine_hearts));

  RemoveCardFromHand(&queen_hearts, &hand);
  RemoveCardFromHand(&king_spades, &hand);
  RemoveCardFromHand(&nine_clubs, &hand);
  RemoveCardFromHand(&nine_hearts, &hand);

  EndTest();
}

void TestWhoWon() {
  StartTest("create_deck");

  Card queen_hearts = {QUEEN, HEARTS, -1};
  Card king_spades = {KING, SPADES, -1};
  Card nine_clubs = {NINE, CLUBS, -1};
  Card nine_hearts = {NINE, HEARTS, -1};

  assert(!WhoWon(&queen_hearts, &king_spades, SPADES));
  assert(!WhoWon(&nine_hearts, &queen_hearts, CLUBS));
  assert(WhoWon(&queen_hearts, &nine_hearts, HEARTS));
  assert(WhoWon(&nine_hearts, &king_spades, HEARTS));
  assert(!WhoWon(&queen_hearts, &nine_clubs, CLUBS));

  EndTest();

}



void Part1Tests() {
  TestCreateDeck();
  TestPushCardToDeck();
  TestPeekAtTopCard();
  TestPopCardFromDeck();
  TestIsDeckEmpty();
  TestPopulateDeck();
  TestShuffle();
}

 void Part2Tests() {
  TestCreateHand();
  TestAddCardToHand();
  TestRemoveCardFromHand();
  TestDeal();
  TestIsLegalMove();
  TestWhoWon();

}

int main(void) {
  srand(21774);

  Part1Tests();
  Part2Tests();

  return EXIT_SUCCESS;
}


