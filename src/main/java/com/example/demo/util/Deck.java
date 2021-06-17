package com.example.demo.util;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;


public class Deck {

    private ArrayList<Card> cardDeck = new ArrayList<Card>();

    public Deck(){ //initialize the deck

        for(int i = 0; i < 5; i++) { //to avoid intiliazing cards with indexoutofbounds value
            Color c = Card.Colors[i];
            if (c == Card.SPECIAL_COLOR ) { //non-numerical cards
                for (int j = 0; j < 2; j++) { // 2 of each
                    getCardDeck().add(new Card(c, Card.REVERSE_CARD));
                    getCardDeck().add(new Card(c, Card.SKIP_CARD));
                    getCardDeck().add(new Card(c, Card.WILD_CARD));
                    getCardDeck().add(new Card(c, Card.DRAW_TWO_CARD));
                }
            } else {
                // Numerical colors card
                // 1 zero card, 2 of each cards for 1-9
                getCardDeck().add(new Card(c, 0));
                for (int k = 0; k < 2; k++){
                    for (int j = 1; j < 10; j++){
                        getCardDeck().add(new Card(c, j));
                    }
                }
            }
        }

    }

    public void shuffle(){
        Collections.shuffle(getCardDeck());
    }

    public int returnSize() {return getCardDeck().size();}


    public ArrayList<com.example.demo.util.Card> getCardDeck() {
        return cardDeck;
    }

    public void setCardDeck(ArrayList<com.example.demo.util.Card> cardDeck) {
        this.cardDeck = cardDeck;
    }
}
