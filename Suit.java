package blackjack;

/*
 * Developer:   Jay Allen
 * Date:        05/15/2019
 * Purpose:     the Suit class builds the cards into their respective suits


package blackjack;

/**
 *
 * @author Jay
 */
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class Suit {

        // private fields
    private String name;
    private String symbol;
    
    public final static Suit CLUBS = new Suit("Clubs", "c");
    public final static Suit DIAMONDS = new Suit("Diamonds", "d");
    public final static Suit HEARTS = new Suit("Hearts", "h");
    public final static Suit SPADES = new Suit("Spades", "s");
    
    public final static List VALUES = 
            Collections.unmodifiableList(
                    Arrays.asList( new Suit[] {CLUBS, DIAMONDS, HEARTS, SPADES}));
    
    private Suit(String nameVal, String symVal) {
        name = nameVal;
        symbol = symVal;
    } // end of Suit
    
    public String getName() {
        return name;
    } // end of getName
    
    public String getSymbol() {
        return symbol;
    } // end of getSymbol
    
    @Override
    public String toString() {
        return name;
    } // end of toString   
    
} // end of Class Suit
