/*
 * Developer:   Jay Allen
 * Date:        05/15/2019
 * Purpose:     Card Class defines what the card is.
 */
package blackjack;

import javafx.scene.image.Image;

/**
 *
 * @author Jay
 */
public class Card {

        // private properties
    private Suit suitVal;
    private Rank rankVal;
    private Image image;
    
        // constructor
    public Card (Suit suit, Rank rank, Image face) {
         image = face;
         suitVal = suit;
         rankVal = rank;
     } // end of Card   
    
        // getters/setters
    public Suit getSuit() {
         return suitVal;
     } // end of getSuit
     
    public Rank getRank() {
         return rankVal;
     } // end of getRank
     
    public int getValue() {
         String rank = rankVal.getSymbol();
         try{
             return Integer.parseInt(rank);
         }catch (Exception ex) {
             if (rank.equals("a")) {
                 return 11; // its an ace
             } // end of if
             else {
                 return 10; // face card
             } // end of else
         } // end of catch
     } // end of getValue
     
    public static String getImgFileName (Suit suit, Rank rank) 
    {
         return ("file:/images/" + suit.getSymbol() + rank.getSymbol() + ".png");
     } // end of getImg
     
    public Image getImage() {
         return image;
     } // end of getImage
    
        // public methods
    public String rankToString() {
        return rankVal.toString();
    } // end of rankToString
    
    public String suitToString() {
        return suitVal.toString();
    } // end of suitToString
    
} // end of card class
