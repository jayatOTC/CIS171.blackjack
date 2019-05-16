/*
 * Developer:   Jay Allen
 * Date:        05/15/2019
 * Purpose:     The Hand class defines each players cards in their hand for each game.
 */
package blackjack;

import java.util.*;
/**
 *
 * @author Jay
 */
public class Hand {

         // private fields
    private int total;
    private int soft;
    private List hand = new ArrayList();
    
    public void addCard(Card card) {
        total += card.getValue();
        if (card.getRank() == Rank.ACE){
            soft += 1;
        } // end of if
        if (soft > 0){
            if(total > 21){
                total -= 10;
                soft -= 1;
            } // end of if
        } // end of if
        hand.add(card);
    } // end of addCard
    
    public Card getCard(int index) {
        return (Card) hand.get(index);
    } // end of getCard
    
    public void discard() {
        hand.clear();
        total = 0;
        soft = 0;
    } // end of discard
    
    public int getHandSize() {
        return hand.size();
    } // end of getHandSize
    
    public void sort() {
        Collections.sort(hand);
    } // not sure i need this
    
    public boolean handEmpty() {
        return hand.isEmpty();
    } // end of handEmpty
    
    public int findCard(Card card) {
        return hand.indexOf(card);
    } // end of findCard
    
    public int getSoft() {
        return soft;
    } // end of getSoft
    
    public int scoreHand() {
        return total;
    } // end of scoreHand
    
    @Override
    public String toString() {
        return hand.toString();
    } // end of toString
    
} // end of class Hand
