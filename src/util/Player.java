package util;

import java.util.ArrayList;

public class Player {

    public String name; //Player's name
    public int numCards; //Number of cards player has
    private ArrayList<Card> hand = new ArrayList<Card>(); //The player's hand

    public Player(String newName){
        this.name = newName;
    }


    public ArrayList<Card> getHand() {
        return hand;
    }

    public String getName() {
        return name;
    }

    public void setHand(ArrayList<Card> hand) {
        this.hand = hand;
    }

    public void addCard(Card c) {
        hand.add(c);
    }

    public void removeCard(Card c) {
        hand.remove(c);
    }

    public int getSize() {
        return hand.size();
    }
}
