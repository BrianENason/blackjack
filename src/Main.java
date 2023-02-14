import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IllegalAccessException {

        int numDeck = 6; // Number of decks for the game
        int shuffleCount = 20 + (int)(Math.random() * ((80 - 20) + 1)); // Sets random shuffle count for increased randomness

        Boolean logDeck = false; // Boolean to control whether a log file should be created
        File deckLog = null; // Variable to hold the deckLog file

        // This will create a new file to write the deckLog into
        if (logDeck) {
            deckLog = writeToFile.createFile();
        }

        DeckOfCards deck = new DeckOfCards(numDeck); // Create a new deck of cards
        ArrayList<Card> shuffledDeck; // Create a holder for the shuffled deck so a new deck doesn't have to be created all the time
        System.out.println(deck.getDeck().size()); //FIXME: Debug purposes - delete before release
        Player player1 = new Player(); // Create a new player object
        Dealer dealer = new Dealer(); // Create a new dealer object
        Integer yellowCard = deck.getYellowCard(numDeck); // This will be the "Yellow Card"


        shuffledDeck = deck.getDeck(); // Put the "Out of Box" deck into the shuffled deck variable to preserve OOB Deck
        System.out.println("Deck Start: " + shuffledDeck); //FIXME: Debug purposes - delete before release

        shuffledDeck = deck.shuffleDeck(shuffleCount, shuffledDeck, deckLog, logDeck);

        System.out.println("Size of shuffled deck = " + shuffledDeck.size()); //FIXME: Debug purposes - delete before release

        //FIXME: Add in a random number to add stopper to the deck before deal (yellow card)

        System.out.println("Random Yellow Card Placement = " + yellowCard);

        for (int i = 0; i < 22; ++i) {
            Card dealCard = shuffledDeck.remove(0);
            System.out.println(dealCard);
        }

        System.out.println(shuffledDeck.size());


        // FIXME: Delete all below commented methods before release

        // This was sent to the DeckOfCards class.
        /*for (int i = 0; i < shuffleCount; ++i) {
            System.out.println("Shuffle Count: " + (i + 1));
            shuffledDeck = deck.oldSchoolShuffle(shuffledDeck);

            if (i % 2 == 0) {
                shuffledDeck = deck.cutDeck(shuffledDeck);
            }

            if (i % 5 == 0) {
                shuffledDeck = deck.shuffleCards(shuffledDeck);
            }

            System.out.println("Deck after shuffle " + (i + 1) + ": " + shuffledDeck);

            if (logDeck) {
                writeToFile.writeDeckToFile(shuffledDeck, deckLog);
            }
        }*/
    }
}

/*
    int shuffleAmount = 50;
        while (shuffleAmount >= 0) {
                shuffledDeck = deck.shuffleCards(shuffledDeck);
                System.out.println(shuffledDeck);
                shuffleAmount -= 1;
                }
                System.out.println(shuffledDeck);
                System.out.println(shuffledDeck.get(7).value);
                ArrayList<Card> newCutDeck = deck.newCutDeck(shuffledDeck, 7);
        System.out.println(newCutDeck); */
