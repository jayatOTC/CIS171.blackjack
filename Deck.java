/*
 * Developer:   Jay Allen
 * Date:        05/15/2019
 * Purpose:     The Deck defines what the deck is.
 */

/**
 *
 * @author Jay
 */

package blackjack;

import java.util.List;
import javafx.scene.image.Image;
import java.util.*;


public class Deck {

       // private properties
    private List deck;
    private int index;
    
    public Deck(){ this(1); }

    public Deck (int numDecks) {
        deck = new ArrayList();
        index = 0;
        
        try {
            for (int i = 0; i < numDecks; i++){
                Iterator suitIterator = Suit.VALUES.iterator();
                while (suitIterator.hasNext()) {
                    Suit suit = (Suit) suitIterator.next();
                    Iterator rankIterator = Rank.VALUES.iterator();
                    while (rankIterator.hasNext()){
                        Rank rank = (Rank) rankIterator.next();
                        Card card = new Card(suit, rank, new Image(Card.getImgFileName(suit, rank)));
//                        System.out.println(Card.getImgFileName(suit, rank));
                        addCard(card);
                    } // end of inner while
                } // end of ewhile
            } // end of for loop
             shuffle();
        } // end of try       
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        } // end of catch
    } // end of Deck

    public void addCard(Card card) {
        deck.add(card);
    } // end of addCard
    
    public int getDeckSize() {
        return deck.size();
    } // end of getDeckSize
    
    public int getNumRemain() {
        return deck.size() - index;
    } // end of getNumRemain
    
    public Card deal() {
        if (index >= deck.size()){
            return null;
        } // end of if
        else {
            return (Card) deck.get(index++);
        } // end of else
    } // end of deal
    
    public void shuffle() {
        Collections.shuffle(deck);
    } // end of shuffle
    
    public boolean deckEmpty() {
        if (index >= deck.size()){
            return true;
        } // end of if
        else {
            return false;
        } // end of else
    } // end of deckEmpty
    
    public void newDeck() {
        index = 0;
    } // end of newDeck
    // methods
} // end of class Deck
