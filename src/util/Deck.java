package util;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;


public class Deck {

    public ArrayList<Card> cardDeck = new ArrayList<Card>();

    public Deck(){ //initialize the deck

        for(Color c: Card.Colors) {
            if (c == Card.SPECIAL_COLOR ) { //non-numerical cards
                for (int i = 0; i < 2; i++) { // 2 of each
                    cardDeck.add(new Card(c, Card.REVERSE_CARD));
                    cardDeck.add(new Card(c, Card.SKIP_CARD));
                    cardDeck.add(new Card(c, Card.WILD_CARD));
                    cardDeck.add(new Card(c, Card.DRAW_TWO_CARD));
                }
            } else {
                // Numerical colors card
                // 1 zero card, 2 of each cards for 1-9
                cardDeck.add(new Card(c, 0));
                for (int i = 0; i < 2; i++){
                    for (int j = 1; j < 10; j++){
                        cardDeck.add(new Card(c, j));
                    }
                }
            }
        }

    }

    public void shuffle(){
        Collections.shuffle(cardDeck);
    }

    public int returnSize() {return cardDeck.size();}





}
