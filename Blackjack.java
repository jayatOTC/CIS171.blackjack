
package blackjack;
/*
 * Developer:   Jay Allen
 * Date:        05/15/2019
 * Purpose:     Create a BlackJack Game to play against the Computer.
 *              Each player is dealt 2 cards from a single deck (52).  
 *		The deck is comprised of cards taken from .png files stored in an image folder.
 *		the deck will be shuffled two cards will be dealt to the user and one to the dealer face down.
 *		After seeing his hand, the user and the computer are given the oppotunity 
 *              to take additional cards.  The computer will take additional cards until 
 *              his card values are greater than 16.
 *		The winner is closest to 21 without going over.  The user will bust if he goes over 21.
 *		A Draw is possible if both the user and computers totals are equal.
 */
/**
 *
 * @author Jay
 */


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import static javafx.application.Application.launch;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
public class Blackjack extends Application{

 
/**
 *
 * @author jh0375800
 */
    private final Deck deck = new Deck(1);
    private final Hand hand = new Hand();
    private final Hand dealer = new Hand();
    private boolean bust;
    private boolean turn;
    
    FlowPane cards = new FlowPane(Orientation.VERTICAL);
    FlowPane dealCards = new FlowPane(Orientation.VERTICAL);
    Label handTotal = new Label();
    Label dealTotal = new Label();    
    Label lblHand = new Label("Your Hand");
    Label lblDeal = new Label("Dealer Hand");    
    Label status = new Label();
    Image imgTable = new Image("file:/images/table.png");
    
    public void newDeck() {
        deck.newDeck();
        deck.shuffle();
    } // end of newDeck
    
    public void newHand() {
        if (deck.getNumRemain() <= deck.getDeckSize() * 0.25) {
            newDeck();
        } // end of if
        
        hand.discard();
        dealer.discard();
        cards.getChildren().removeAll(cards.getChildren());
        dealCards.getChildren().removeAll(dealCards.getChildren());
        handTotal.setText("");
        dealTotal.setText("");
        
        bust = false;
        turn = true;
        
        drawCard(hand, cards, handTotal);
        drawCard(dealer, dealCards, dealTotal);
        drawCard(hand, cards, handTotal);
        
        status.setText("Your Turn");
        
    } // end of newHand
    
    public void drawCard(Hand hand, FlowPane pane, Label l){
        try{
//            new SlowPlay(6);
//            Thread.sleep(300);
            Card card = deck.deal();
            ImageView img = new ImageView(card.getImage());
            pane.getChildren().add(img);
            hand.addCard(card);
            
            int handTotal = hand.scoreHand();
            
            StringBuilder total = new StringBuilder();
            if(hand.getSoft() > 0) {
                total.append(handTotal - 10).append("/");
            } // end of if
            total.append(handTotal);
            l.setText(total.toString());
        } // end of try
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        } // end of catch        
    } // end of drawCard
    
    @Override
    public void start(Stage primaryStage) {
        
            // set label fonts and colors
        handTotal.setFont(new Font("Arial Bold", 24));
        handTotal.setTextFill(Color.BLUE);        
        dealTotal.setFont(new Font("Arial Bold", 24));
        dealTotal.setTextFill(Color.WHITE);        
        status.setFont(new Font("Arial Bold", 24));
        status.setTextFill(Color.WHITE);        
        lblHand.setFont(new Font("Arial Bold", 24));
        lblHand.setTextFill(Color.WHITE);        
        lblDeal.setFont(new Font("Arial Bold", 24));
        lblDeal.setTextFill(Color.WHITE);
        
        // ******************************
            // Button and their action events

            // button for new hand
        Button btnNew = new Button("New Hand");
        btnNew.setOnAction((e) -> {
            newHand();
        });
        
            // button to close program
        Button btnClose = new Button("Exit Game");
        btnClose.setOnAction((e) -> {
            System.exit(0);
        });
        
            // hit button for player
        Button btnDraw = new Button("Hit");
        btnDraw.setOnAction((e) -> {
           if (turn == true && bust != true){
               drawCard(hand, cards, handTotal);               
               if (hand.scoreHand() > 21) {
                   bust = true;
                   turn = false;
                   status.setText("Busted!!");
               } // end of inner if
           }  // end of if           
        }); // end of btnDraw
        
            // stand button gives control to dealer  
            // want to slow this part down but cant figure it out
        Button btnStnd = new Button("Stand");
        btnStnd.setOnAction((ActionEvent e) ->{
            if (turn == true && bust!= true) {
                turn = false;
                while(dealer.scoreHand() < 17) {
                    drawCard(dealer, dealCards, dealTotal); 

                } // end of while
                
                int pScore = hand.scoreHand();     // tracks player score
                int dlScore = dealer.scoreHand();     // tracks dealer score
                
                if (dlScore <=21 && pScore == dlScore) {
                    String score = String.format("Tie %s. It's a Push!!", dlScore);
                    status.setText(score);                    
                } // end of if tie
                else if (dlScore <= 21 && pScore <= dlScore) {
                    status.setText("You have Lost");
                } // end of else if
                else {
                    status.setText("You have Won!!!!");
                } // end of else                
            } // end of if
        }); // end of btnStnd        
        
        // ***************** end of buttons
        
            // begin placing/building display
        BackgroundSize bkGrnSize = new BackgroundSize(100, 100, true,true,true, false);
        BackgroundImage bkGrnImg = new BackgroundImage(imgTable, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, bkGrnSize);
        Background bckGrnd = new Background(bkGrnImg);        
        
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        grid.setHgap(5.5);
        grid.setVgap(5.5); 
        
        grid.add(dealCards, 0, 0, 3, 1); 
        grid.add(lblDeal, 0, 1);
        grid.add(dealTotal, 1, 1, 2, 1); 
        
        // padding
        Pane p = new Pane(); 
        p.setPrefSize(0, 100); 
        grid.add(p, 0, 2);         
        grid.add(cards, 0, 3, 3, 1); 
//        System.out.println(cards.toString());     // used to find error in display of cards
        grid.add(lblHand, 0, 4);
        grid.add(handTotal, 1, 4, 2, 1);  
        grid.add(btnDraw, 0, 5);
        grid.add(btnStnd, 1, 5);
        grid.add(btnNew, 2, 5); 
        grid.add(btnClose, 3, 5);
        grid.add(status, 0, 6, 3, 1);
        grid.setBackground(bckGrnd);
        
        Scene scene = new Scene(grid, 1000, 700);
        
        primaryStage.setTitle("BlackJack");
        primaryStage.setScene(scene);
        primaryStage.show();
                
        newDeck(); 
        newHand(); 
        
    } // end of start
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    } // end of main
    
} // end of BlackJack

