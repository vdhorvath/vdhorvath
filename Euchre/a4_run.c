#include <stdio.h>
#include <time.h>
#include <stdlib.h>

#include "a4.h"

#define PRINT_OUT_DECK 0

#define PRINT_P1_HAND 0

#define MAX_INPUT 5

Card* TakePlayer2Turn(Hand *p2hand);


Card* TakePlayer1Turn(Hand *p1hand) {
  printf("Player 1 played the card: ");

  PrintCard(p1hand->first_card->this_card);
  Card* out = p1hand->first_card->this_card;
  RemoveCardFromHand(p1hand->first_card->this_card, p1hand);
  printf("\n\n");

  return out;
}

Card* TakeComputerTurn(Hand *p1hand) {
  printf("The computer played the card: ");

  PrintCard(p1hand->first_card->this_card);
  Card* out = p1hand->first_card->this_card;
  RemoveCardFromHand(p1hand->first_card->this_card, p1hand);
  printf("\n\n");

  return out;
}

void FlushIn() {
  char c;
  while ((c = getchar()) != '\n' && c != EOF) {
  }
}

Card* ComputerFollow(Hand *c_hand, Card *led_card, Suit trump) {
  // Iterate through hand
  // If a card is legal to play, play it.
  int i = 0;

  CardNode *cih = c_hand->first_card;

  while (!IsLegalMove(c_hand, led_card, cih->this_card)) {
    cih = cih->next_card;
    i++;
  }
  printf("Computer played the card: ");
  PrintCard(cih->this_card);
  printf("\n");

  Card* out = cih->this_card;
  RemoveCardFromHand(cih->this_card, c_hand);
  return out;
}

Card* ComputerLead(Hand *c_hand) {
  PrintCard(c_hand->first_card->this_card);
  Card* out = c_hand->first_card->this_card;
  RemoveCardFromHand(c_hand->first_card->this_card, c_hand);
  printf("\n\n");

  return out;
}

Card* PlayerFollow(Hand *hand) {
  printf("Player 2's turn: Which card do you want to play?\n");
  return TakePlayer2Turn(hand);
}

Card* PlayerLead(Hand *hand) {
  printf("Player 2's turn: Which card do you want to lead?\n");
  return TakePlayer2Turn(hand);
}

Card* TakePlayer2Turn(Hand *p2hand) {
  PrintHand(p2hand);
  printf("\n");

  int which_one = 0;
  scanf("%d", &which_one);

  while (which_one > (p2hand->num_cards_in_hand-1)) {
    printf("Please choose a number between %d and %d. ",
           0,
           (p2hand->num_cards_in_hand-1));
    scanf("%d", &which_one);
  }

  FlushIn();

  int i = 0;

  CardNode *cih = p2hand->first_card;

  while (i < which_one) {
    cih = cih->next_card;
    i++;
  }
  printf("Player 2 played the card: ");
  PrintCard(cih->this_card);
  printf("\n");

  Card* out = cih->this_card;
  RemoveCardFromHand(cih->this_card, p2hand);

  return out;
}


Suit GetTrump(Card *topcard) {
  // TODO(ahs): Pick the card up, or no
  // TODO(ahs): Choose the trump

  printf("Time to choose trump!\n");

  printf("\n\nIf you'd like, you can call trump. If not, 'p'ass.\n");
  printf("'S'pades, 'C'lubs, 'H'earts, 'D'iamonds\n");

  char input[MAX_INPUT];
  char which_suit = 'p';

  // TODO(ahs): Replace with getChar() and flush
  if (fgets(input, MAX_INPUT, stdin) &&
      sscanf(input, "%c", &which_suit) != 1) {
    which_suit = 'p';
  }

  switch (which_suit) {
    case 'S':
    case 's':
      return SPADES;
    case 'C':
    case 'c':
      return CLUBS;
    case 'H':
    case 'h':
      return HEARTS;
    case 'D':
    case 'd':
      return DIAMONDS;
    default:
    case 'p':
    case 'P':
      return rand()%4;  // TODO(ahs): replace with rand_r
      break;
  }
}

void PrintTrump(Suit trump) {
  printf("\n\n*************************\n");
  printf("**** Trump is %s *****\n", GetSuit(trump));
  printf("*************************\n\n");
}


void PrintScore(int score1, int score2) {
  printf("Player 1: %d\tPlayer 2: %d", score1, score2);
}

// Returns 1 or 2, based on who won.
int PlayRound(Deck *game_deck) {
  Shuffle(game_deck);

  if (PRINT_OUT_DECK) {
    PrintDeck(game_deck);
  }

  // Deal the hand
  Hand *p1hand = CreateHand();
  Hand *p2hand = CreateHand();

  Deal(game_deck, p1hand, p2hand);

  printf("\n\n");

  if (PRINT_P1_HAND) {
    printf("Player 1: \n");
    PrintHand(p1hand);
  }

  printf("You've been dealt: \n");
  PrintHand(p2hand);

  // For each round:

  // Determine trump
  Suit trump = GetTrump(PeekAtTopCard(game_deck));
  PrintTrump(trump);

  // TODO(ahs): Remember who called trump!

  // Start playing.
  int num_tricks = 0;
  int p1score = 0, p2score = 0;
  Card *led_card, *followed_card;

  printf("\n\n");
  printf("Starting the round...");

  int leader = 0;  // Player

  while (num_tricks < kNumCardsInDeck) {
    if (leader) {
      led_card = ComputerLead(p1hand);
      followed_card = PlayerFollow(p2hand);
    } else {
      led_card = PlayerLead(p2hand);
      followed_card = ComputerFollow(p1hand, led_card, trump);
    }

  // 1 for leader, 0 for follower
  int trick_winner = WhoWon(led_card, followed_card, trump);

  if (trick_winner == 1) {
    // The leader won, so keeps leading
    if (leader == 0) {
      printf("Player (leader) won the trick\n");
    } else {
      printf("Computer (leader) won the trick\n");
    }
  } else {
    if (leader == 0) {
      printf("Computer (follower) won the trick\n");
      leader = 1;  // Computer becomes the leader
    } else {
      printf("Player (follower) won the trick\n");
      leader = 0;  // Player becomes the leader
    }
  }
    num_tricks++;
  }

  if (p2score > 0) {
    printf("\n\n");
    printf("Player %d won this round with %d tricks!\n",
      (p1score > p2score ? 1 : 2),
      (p1score > p2score ? p1score : p2score));
  }

  ReturnHandToDeck(p1hand, game_deck);
  ReturnHandToDeck(p2hand, game_deck);

  DestroyHand(p1hand);
  DestroyHand(p2hand);

  return (p1score > p2score ? 1 : 2);
}

int PlayGame(Deck *game_deck) {
  int num_rounds = 5;
  int which_player_won, i;
  int player1score = 0, player2score = 0;

  for (i = 0; i < num_rounds; i++) {
    printf("\n\n===========================\n");
    printf("Round # %d\n", i+1);
    printf("===========================\n\n");

    which_player_won = PlayRound(game_deck);

    if (which_player_won == 1) {
      player1score++;
    }    else {
      player2score++;
    }

    printf("\n\n\n");
    printf("Game Score so far: \n");
    PrintScore(player1score, player2score);
    printf("\n===========================\n\n");

    char input[5];

    printf("When you're ready, press <enter> to go to the next round. \n");
    char whichOne = getchar();
  }
  printf("\n\n");
  printf("Player %d won the game!\n",
    (player1score > player2score ? 1 : 2));

  PrintScore(player1score, player2score);

  printf("\n\n");
  return (player1score > player2score ? 1 : 2);
}

int main() {
  srand(time(NULL));

  printf("Welcome to NEUchre!\n");
  printf("When you're ready to play, press <enter>\n");

  char in;
  in = getchar();

  printf("Okay. ");

  Deck *game_deck = PopulateDeck();

  if (PRINT_OUT_DECK) {
    PrintDeck(game_deck);
  }

  printf("Would you like to play a [R]ound or a [G]ame?\n");

  // TODO(ahs): Use getChar/Flush instead?
  char input[MAX_INPUT];
  if (fgets(input, MAX_INPUT, stdin) &&
     sscanf(input, "%c", &in) != 1 ) {
  }

  switch (in) {
    case 'r':
      PlayRound(game_deck);
      break;
    case 'g':
      PlayGame(game_deck);
      break;
    default:
      printf("Quitting the game. ");
  }

  // Don't forget to free the deck!
  // I created it in this function, going to free it in this function.
  DestroyDeck(game_deck);
}
