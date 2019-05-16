/*
 * Developer:   Jay Allen
 * Date:        05/15/2019
 * Purpose:     Hand defines the players hand.
 */
package blackjack;

/**
 *
 * @author Jay
 */
public class Player {

        // private
    protected String name;
    protected int score;
    protected Hand hand;        // need to build class hand

        // public fields
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Hand getHand() {
        return hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }
    
        // constructors
    // need partial and modify setters for score and cards
    
    
} // end of Player
