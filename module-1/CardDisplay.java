/*
 * Justin Bradley
 * CSD 420 Advanced Java Programming
 * Module 1.3 Programming Assignment
 * Date: October 25, 2026
 *
 * Purpose:
 *   JavaFX program that displays four cards randomly selected from a
 *   standard 52 card deck. The card images (1.png through 52.png) are
 *   stored in a "cards" subdirectory next to this program. A Refresh
 *   button below the cards deals four new random cards. Event handling
 *   is done with a lambda expression.
 *
 * How to run (from the folder that contains this file and the cards folder):
 *   javac --module-path "%PATH_TO_FX%" --add-modules javafx.controls CardDisplay.java
 *   java  --module-path "%PATH_TO_FX%" --add-modules javafx.controls CardDisplay
 *
 * Resources:
 *   Liang, Y. D. (2020). Introduction to Java Programming and Data Structures
 *   (12th ed.). Pearson. Chapters 14 and 15 (JavaFX basics, event-driven
 *   programming, and lambda expressions).
 *   OpenJFX documentation: https://openjfx.io/
 */

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CardDisplay extends Application {

    // Number of cards in the deck and number of cards shown at once
    private static final int DECK_SIZE = 52;
    private static final int CARDS_SHOWN = 4;

    // Folder that holds the card images 1.png through 52.png
    private static final String CARD_FOLDER = "cards";

    // One ImageView per displayed card, reused on every refresh
    private final ImageView[] cardViews = new ImageView[CARDS_SHOWN];

    // Card numbers 1 to 52; shuffled to pick random, non-repeating cards
    private final List<Integer> deck = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) {
        // Build the deck of card numbers once
        for (int i = 1; i <= DECK_SIZE; i++) {
            deck.add(i);
        }

        // Row of four card images
        HBox cardRow = new HBox(10);
        cardRow.setAlignment(Pos.CENTER);
        for (int i = 0; i < CARDS_SHOWN; i++) {
            cardViews[i] = new ImageView();
            cardViews[i].setPreserveRatio(true);
            cardViews[i].setFitWidth(100);
            cardRow.getChildren().add(cardViews[i]);
        }

        // Refresh button below the cards; lambda expression handles the click
        Button btRefresh = new Button("Refresh");
        btRefresh.setOnAction(e -> dealCards());

        // Stack the card row on top of the button
        VBox root = new VBox(15, cardRow, btRefresh);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));

        // Show the first hand before the window opens
        dealCards();

        primaryStage.setTitle("Module 1.3 - Random Cards");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    /**
     * Shuffles the deck and shows the first four cards. Because the deck
     * holds each card number once, the four cards are always different.
     */
    private void dealCards() {
        Collections.shuffle(deck);
        for (int i = 0; i < CARDS_SHOWN; i++) {
            cardViews[i].setImage(loadCard(deck.get(i)));
        }
    }

    /**
     * Loads one card image from the cards subdirectory.
     *
     * @param cardNumber card number from 1 to 52
     * @return the Image for that card
     */
    private Image loadCard(int cardNumber) {
        File file = new File(CARD_FOLDER, cardNumber + ".png");
        if (!file.exists()) {
            // Helpful message if the program is run from the wrong folder
            System.err.println("Missing card image: " + file.getAbsolutePath());
        }
        return new Image(file.toURI().toString());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
