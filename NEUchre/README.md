# README: Euchre (pronounced "U-ker")


![Euchre, card game](euchre.png)


**What is Euchre?**

Euchre is a 2-player game. The player with the most points after 5 rounds wins. A player wins a round by "collecting the most tricks". A round consists of each player being dealt 5 cards, choosing a trump (a suit designated as the highest/most powerful for this round), and then each player takes turns playing cards for a "trick". A player wins a trick by playing the highest value card. There are a couple of rules that must be followed for playing a card: the first player can play any card, but the second player must "follow suit": if they have a card of the same suit, they must play it (but they can choose which card they want to play). Further, a card of the trump suit is higher than all other cards in the deck. For example, if Spades is trump, a 9 of Spades is higher than an Ace of Diamonds. Within a suit, including trump, the face value of the card is highest, with Aces being the highest value card.

In this version of the game, Player 1 (the computer) ALWAYS goes first and leads the first card.

The deck used for Euchre is a subset of the traditional 52-card deck. It includes the Ace, King, Queen, Jack, 10 & 9 of all four suits (Spades, Diamonds, Hearts, and Clubs).


This project is an implementation of the 2 player Euchre card game. To get a sense of the game, you can play the traditional version at https://cardgames.io/euchre/

~                                                                                                                                                           
**How to build and run the code**


**Description of the files in the directory**

> a4.c - A players hand is built as a doubly Linked-linked list

> deck.c - Deck implemented as a stack based on an Array  

> a4.h - Header file, containing all the information as it relates to all the function. 

> a4_helpers.c - Other  helper function needed to excute the game.

> a4_run.c is currently built out, this makes everything work together.

> a4_test.c Test file to test all the functions. 

> Makefile - Executes all files. 

 
