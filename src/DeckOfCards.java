import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.floor;

/**
 * This class will construct a "Deck" as well as a "Table Deck" of size (deck(s) needed).
 */
public class DeckOfCards {
    private ArrayList<Card> deck;
    private ArrayList<Card> shuffledDeck;
    private ArrayList<Card> cutDeck;

    // Steps for every class we create:
    // Define Class
    // Define instance variables
    // create constructor
    // create get/set methods
    // create any custom methods

    public DeckOfCards(ArrayList<Card> deck) {
        this.deck = deck;
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }

    public void setDeck(ArrayList<Card> deck) {
        this.deck = deck;
    }

    /**
     * This will construct a deck object of size (deck * 52) wherein each suit (Heart, Spade, Club, Diamond) will be assigned
     * to each card face (Ace, 2, 3, ... , King) for however many decks are being called for.
     *
     * @param numDeck This will take in a value of how many decks need to be created.
     * @throws IllegalAccessException
     */
    public DeckOfCards(int numDeck) throws IllegalAccessException {
        List<String> suits = Card.getValidSuits();
        List<String> faceNames = Card.getValidFaceName();

        deck = new ArrayList<>();

        for (int i = numDeck; i > 0; --i) {
            for (String suit:suits) {
                for (String faceName:faceNames)
                    deck.add(new Card(faceName, suit));
            }
        }
    }

    /**
     * This method will take in a number of "Cards" and create a list of 1's and 0's of length (card number) to be used
     * as a random pull from one pile or another.
     *
     * @param numCards The number of cards that need to be addressed
     * @return An array of 1's and 0's to randomize the pull from 2 separate decks
     */
    public static ArrayList<Integer> fillShuffleArray(int numCards) {
        ArrayList<Integer> drawArray = new ArrayList<>();
        for (int i = 0; i < numCards; ++i) {
            int r = (int)(floor(Math.random() * 501) % 2);
            drawArray.add(r);
        }
        // System.out.println(drawArray);
        return drawArray;
    }

    /**
     * This is an attempt at replicating the traditional "Riffle Shuffle". This is the standard shuffle you see at
     * all tables where the deck is split into two piles, and one-by-one cards are added back together from either
     * of the two piles to form a single deck again.
     *
     * @param deck The "Table Deck" that will be shuffled
     * @return A Shuffled "Table Deck"
     * @throws IllegalAccessException
     */
    public ArrayList<Card> riffleShuffle(ArrayList<Card> deck) throws IllegalAccessException{

        ArrayList<Card> fullShuffledDeck = new ArrayList<>(); // Create a blank object to hold the shuffled deck;
        ArrayList<Integer> shuffleOrder = fillShuffleArray(deck.size()); // pulls a list of 1 and 0 to indicate split (s)1 or 2
        List <Card> s1 = new ArrayList<>(); // This is the first of the 2 "Split Decks"
        int splitPoint = ((deck.size()) / 2); // FIXME: Add some randomness to this so it isn't a perfect split each time

        // This will take the first n-number of cards from the input deck and store them in (split) s1 deck
        for (int i = 0; i < splitPoint; ++i) {
            s1.add(deck.get(0));
            deck.remove(0);
        }

        List s2 = deck; // What wasn't taken into s1 (the remainder of the input deck) becomes (split) s2

        // This will go through the 2 decks and combine them into a single deck.
        for (int i = 0; i < shuffleOrder.size(); ++i) {
            if ((s1.size() != 0) && (s2.size() != 0)) {  // Put to verify that both decks have some cards left in them
                int x = shuffleOrder.get(i);
                if (x == 0) {
                    Card y = (Card) s1.remove(0);
                    fullShuffledDeck.add(y);
                } else {
                    Card y = (Card) s2.remove(0);
                    fullShuffledDeck.add(y);
                }
            } else {  // If one of the 2 decks is empty, then the remainder of the other deck is added to the top of the output deck
                if (s1.size() == 0) {
                    fullShuffledDeck.addAll(s2); //Add rest of s2
                    return fullShuffledDeck;
                } else {
                    fullShuffledDeck.addAll(s1); //Add rest of s1
                    return fullShuffledDeck;
                }
            }
        }
        return fullShuffledDeck;
    }

    /**
     * This method will take in a "Table Deck" of cards as an argument, and then split that "Deck" into 2 sub-decks
     * selected from a random point between 1 and (Deck - 1).
     *
     * @param deck The "Table Deck" to be "cut"
     * @return a "Cut" "Table Deck"
     * @throws IllegalAccessException
     */
    public ArrayList<Card> cutDeck (ArrayList<Card> deck) throws IllegalAccessException {
        cutDeck = new ArrayList<>();

        // This will prevent the "Cut" point from being 0
        int cutLocation = (int) (floor(Math.random()* 1000) % deck.size());

        if (cutLocation == 0) {cutLocation = cutLocation + 1;}

        List bottomCut = deck.subList(0, cutLocation);
        List topCut = deck.subList(cutLocation, deck.size());
        cutDeck.addAll(topCut);
        cutDeck.addAll(bottomCut);
        return cutDeck;

    }

    /**
     * This will perform a mock "Wash Shuffle" - Almost as if a modified wash shuffle.
     * NOTE: A traditional "Wash Shuffle" is where the deck is broken into 2 or more parts, and then the dealer spreads
     * them out on the table and moved around.
     *
     * NOTE: This is typically only done on the first shuffle
     *
     * @param deck The input "Table Deck" to be shuffled
     * @return A "Wash Shuffled" version of the "Table Deck"
     * @throws IllegalAccessException
     */
    public ArrayList<Card> washShuffle(ArrayList<Card> deck) throws IllegalAccessException {
        shuffledDeck = new ArrayList<>();
        for(int i = (deck.size() - 1); i >= 0; --i) {
            double drawCardRand = floor((Math.random()*1000));
            int drawCard = (int) (drawCardRand % i);
            // System.out.println("Draw Card Is: " + deck.getDeck().get(drawCard) + ", remainder in deck is: " + i);
            shuffledDeck.add(deck.get(drawCard));
            deck.remove(drawCard);
        }
        return shuffledDeck;
    }

    /**
     * This method will shuffle the deck using a number of different shuffles as well as a few Random modifiers thrown
     * in to ensure that the "Deck" being used at the "Table" is always random - like in real life with the shuffling
     * of the dealer.
     *
     * @param shuffleCount How many shuffles to happen to the deck
     * @param shuffledDeck The input deck to be shuffled. Called "Shuffled Deck" as it is a carry-over from the main
     * @param deckLog FILE - Tells the program where to record the deck if needed
     * @param logDeck Boolean - Tells the program whether to write the deck to a file for analysis/history
     * @return This returns a fully-shuffled deck that can then be delt from
     * @throws IllegalAccessException
     */
    public ArrayList<Card> shuffleDeck (int shuffleCount, ArrayList<Card> shuffledDeck, File deckLog, Boolean logDeck) throws IllegalAccessException {

        for (int i = 0; i < shuffleCount; ++i) {
            System.out.println("Shuffle Count: " + (i + 1)); //FIXME: Debug purposes - delete before release
            shuffledDeck = riffleShuffle(shuffledDeck); // This will send the deck through a standard "Riffle Shuffle"

            if (i % 2 == 0) { // This will "Cut" the deck after every other shuffle attempt
                shuffledDeck = cutDeck(shuffledDeck);
            }

            if (i % 5 == 0) { // This will send the deck through a "Wash Shuffle" every 5 shuffles to add to the randomness
                shuffledDeck = washShuffle(shuffledDeck);
            }

            System.out.println("Deck after shuffle " + (i + 1) + ": " + shuffledDeck); //FIXME: Debug purposes - delete before release

            if (logDeck) {
                writeToFile.writeDeckToFile(shuffledDeck, deckLog);
            }
        }
        return shuffledDeck;
    }


    /**
     * This method will serve 2 purposes:
     * 1) It will return an index position of a random card between 2 index positions
     * 2) It will return an index position of a random card between 0 and the size of the deck
     *
     * @param min The minimum card position to consider
     * @param max The maximum card position to consider
     * @return an index for the position of the random card and/or a whole number between 0 and the number of cards in the deck
     */
    public Integer getRandomCard(int min, int max) {
        int randomCard = min + (int)(Math.random() * ((max - min) + 1));
        System.out.println("Random Card = " + randomCard); // FIXME: Delete before release
        return randomCard;
    }

    /**
     * The house "Yellow Card" is a marker that is put towards the back of a shuffled table deck to let the dealer
     * know when it is time to "Waste" the rest of the deck and shuffle a new one for the table play.
     *
     * @param numDeck The number of decks in consideration
     * @return Where to "place" the house "Yellow Card" in the table deck to alert when a new shuffle is needed
     */
    public Integer getYellowCard(int numDeck) {
        int yellowCard = 0;
        Double holderPercentBase = .19;
        int max = (numDeck * 52); //The maximum Cards in a table deck
        int randomModifier = getRandomCard(1, 5);

        Double holderPercent = holderPercentBase + (randomModifier / 100);


        int min = max - (int)(max * holderPercent);
        System.out.println("Minimum = " + min); // FIXME: Delete before release

        yellowCard = getRandomCard(min, max);
        // System.out.println(yellowCard); FIXME: Delete before Release

        System.out.println("You will have " + (max - yellowCard) + " cards left in the table deck after the yellow");

        return yellowCard;
    }
}


/* FIXME: Delete all this below

    public DeckOfCards() throws IllegalAccessException {
        List<String> suits = Card.getValidSuits();
        List<String> faceNames = Card.getValidFaceName();

        deck = new ArrayList<>();

        for (String suit:suits) {
            for (String faceName:faceNames)
                deck.add(new Card(faceName, suit));
        }
    }


    public ArrayList<Card> fullShuffle (ArrayList<Card> deck, int numShuffles) throws IllegalAccessException {
        int timesToShuffle = numShuffles;
        ArrayList<Card> fullShuffledDeck = deck;

        while(timesToShuffle >= 0) {
            if (floor(timesToShuffle % 5) == 0) {
                fullShuffledDeck = cutDeck(fullShuffledDeck);
                // System.out.println("Cut Deck: " + fullShuffledDeck.size());
            } else {
                fullShuffledDeck = shuffleCards(fullShuffledDeck);
                // System.out.println("Shuffled Deck: " + fullShuffledDeck);
            }
            // System.out.println("Shuffle Number: " + timesToShuffle + " Deck Is: " + fullShuffledDeck);
            --timesToShuffle;
        }
        return fullShuffledDeck;
    }




    // public ArrayList<Card> shuffleCards (DeckOfCards deck) throws IllegalAccessException {
    public ArrayList<Card> shuffleCards (ArrayList<Card> deck) throws IllegalAccessException {
        shuffledDeck = new ArrayList<>();
        for(int i = (deck.getDeck().size() - 1); i >= 0; --i) {
            double drawCardRand = floor((Math.random()*1000));
            int drawCard = (int) (drawCardRand % i);
            // System.out.println("Draw Card Is: " + deck.getDeck().get(drawCard) + ", remainder in deck is: " + i);
            shuffledDeck.add(deck.getDeck().get(drawCard));
            deck.getDeck().remove(drawCard);
        }
        return shuffledDeck;
    }*/


    /*public ArrayList<Card> cutDeck (ArrayList<Card> deck, int numberCuts) throws IllegalAccessException {
        cutDeck = new ArrayList<>();
        for (int i = numberCuts; i >=0; --i) {
            int cutLocation = (int) (floor(Math.random()* 1000) % deck.size());
            List bottomCut = deck.subList(0, cutLocation);
            List topCut = deck.subList(cutLocation, deck.size());
            // System.out.println("Deck: " + deck);
            // System.out.println("Bottom Cut: " + bottomCut);
            // System.out.println("Top Cut: " + topCut);
            cutDeck.addAll(topCut);
            cutDeck.addAll(bottomCut);
            // System.out.println(cutDeck);
        }
        return cutDeck;
        // return deck;
    }*/