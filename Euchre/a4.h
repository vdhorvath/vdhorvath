#ifndef A4_H
#define A4_H

#define kNumCardsInHand 5
#define kNumCardsInDeck 24

enum suit {HEARTS, CLUBS, SPADES, DIAMONDS};

typedef enum suit Suit;

enum name {NINE = 9, TEN, JACK, QUEEN, KING, ACE};

typedef enum name Name;

// A note: The value field is here for challenge problems.
// You can safely ignore it untill you are working on challenges.
struct card {
  Name name;  // The name of a card, such as Ace, King, etc.
  Suit suit;  // The suit of a card (Spades, Diamond, etc)
  int value;  // The value of a card (which changes depending on what trump is)
};

typedef struct card Card;

struct deck {
  Card* cards[kNumCardsInDeck];
  int top_card;
};

typedef struct deck Deck;

struct game_score {
  int player1_score;
  int player2_score;
};

typedef struct game_score GameScore;

typedef struct card_node CardNode;

struct card_node {
  CardNode *next_card;
  CardNode *prev_card;
  Card *this_card;
};

struct hand {
  int num_cards_in_hand;
  CardNode *first_card;
};

typedef struct hand Hand;

/*
 * NOTES:
Here is a summary of how the three structures are
intended to work in regards to memory:
For each round of the game, you create 2 new Hands,
deal Cards from the Deck to the Hands. As Players play
Cards, the cards go back to the Deck (removed from the
Hand; the CardNode is freed). At the end of the round,
the Hands go away (are freed), but the Cards are now
in the Deck and can be re-used for the next round.
The function returnHandToDeck() is a helper that
takes all the Cards out of the Hand and returns them to
the Deck.
At the end of the game, destroy the Deck by removing all
the Cards from the Deck, free them, and free the Deck.
*/

//----------------------------------------
// Deck functions
//----------------------------------------
// Implement these functions in deck.c.
// Creates the deck to be used for NEUchre.
// Assume that the value of cards are:
// Nine=9; Ten=10; Jack=11; and so on, up to Ace=14.

// Creates the deck, initializing any fields necessary.
// Returns a pointer to the deck, which has been allocated on the heap.
Deck* CreateDeck();

// Adds a card to the top of the deck.
// Returns a pointer to the deck.
Deck* PushCardToDeck(Card*, Deck*);

// Shows the top card, but does not remove it from the stack.
// Returns a pointer to the top card.
// If the deck is empty, return NULL. 
Card* PeekAtTopCard(Deck* deck);

// Removes the top card from the deck and returns it.
// The card should NOT be freed at this point; it is the
// responsibility of the caller to free the card at the
// appropriate time.
// Returns a pointer to the top card in the deck.
// If the deck is empty, return NULL. 
Card* PopCardFromDeck(Deck* deck);

// Determines if the deck is empty.
// Returns 1 if the Deck is empty, 0 otherwise.
int IsDeckEmpty(Deck* deck);

// Destroys the deck, freeing any memory allocated
// for this deck (the cards and the deck).
// DestroyDeck should call DestroyCard on all of the
// cards in the deck.
void DestroyDeck(Deck* deck);

//----------------------------------------
// Card functions
//----------------------------------------

// Creates a card, initializing the suit and name to that specified.
// Returns a pointer to the new card, which has beel allocated on the heap.
// It is the responsibility of the caller to call destroyCard()
// when it is done with the Card.
Card* CreateCard(Suit, Name);

// Destroys the card, freeing any memory allocated for the card.
void DestroyCard(Card* card);

//----------------------------------------
// Hand functions
//----------------------------------------

// Creates a Hand struct and initializes any necessary fields.
// Returns a pointer to the new Hand, which has been allocated on the heap.
Hand* CreateHand();

// Adds a card to the hand.
void AddCardToHand(Card *card, Hand *hand);

// Removes a card from the hand.
// Does not free the card; it is the responsibility
// of the caller to free the card at the appropriate
// time.
// Returns a pointer to the card that is no longer in the hand.
Card* RemoveCardFromHand(Card *card, Hand *hand);

// Determines if there are any cards in the hand.
// Return 1 if the hand is empty; 0 otherwise.
int IsHandEmpty(Hand* hand);

// Destroys the hand, freeing any memory allocated for it.
void DestroyHand(Hand* hand);

//----------------------------------------
// Game functions
//----------------------------------------

// Shuffle the deck.
// Put them in a random order.
void Shuffle(Deck *deck);

// Given a deck (assume that it is already shuffled),
// take the top card from the deck and alternately give
// it to player 1 and player 2, until they both have
// kNumCardsInHand.
void Deal(Deck *aDeck, Hand *p1hand, Hand *p2hand);

// Create a Deck for this game, and add any
// needed cards to the deck.
// Return a pointer to the deck to be used for the game
Deck* PopulateDeck();

// Given a lead card, a players hand, and the card the player wants
// to play, is it legal?
// If the player has a card of the same suit as the ledCard, they
// must play a card of the same suit.
// If the player does not have a card of the same suit, they can
// play any card.
// Returns 1 if the move is legal; 0 otherwise.
int IsLegalMove(Hand *hand, Card *lead_card, Card *played_card);


// Given two cards that are played in a hand, which one wins?
// If the suits are the same, the higher card name wins.
// If the suits are not the same, player 1 wins, unless player 2 played trump.
// Returns 1 if the person who led won, 0 if the person who followed won.
int WhoWon(Card *lead_card, Card *followed_card, Suit trump);

// Take all the cards out of a given hand, and put them
// back into the deck.
void ReturnHandToDeck(Hand *hand, Deck *deck);

//----------------------------------------
// Challenge functions
//----------------------------------------

// Considering this for a challenge problem.
Card* GetBestMove(Hand *my_hand, Card *led_card, Suit trump);

// Sort the given hand in descending order of power.
//
// The sort order should be: all cards of the given trump should
// be the "highest", and A high down to 9;
// The other suits can be in random order, but the card values must go
// from high to low.
void SortHand(Hand *hand, Suit trump);

void ShuffleHand(Hand* hand);

//----------------------------------------
// Helper functions
//----------------------------------------

void PrintCard(Card *a_card);

void PrintHand(Hand *a_hand);

void PrintDeck(Deck *a_deck);

char *GetName(Name a_nard_name);

char *GetSuit(Suit a_card_suit);

#endif
