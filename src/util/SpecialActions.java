package util;

import javax.sound.sampled.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;


public class SpecialActions {
    int round;
    Player mActivePlayer;
    Player nextPlayer;
    Player prevPlayer;
    Deck mActiveDeck;
    ArrayList<Player> mPlayers = new ArrayList<Player>();
    Card mTopPileCard;
    Actions actions;
    Card resultCard;
    Card moveStatus;
    Card tempCard = new Card(Color.BLUE, 2);

    public SpecialActions(Deck d, ArrayList<Player> p, Card c){
        this.round = 0;
        this.mPlayers = p;
        this.mActivePlayer = mPlayers.get(0);
        this.nextPlayer = mPlayers.get(1);
        this.prevPlayer = mPlayers.get(mPlayers.size() - 1);
        this.mActiveDeck = d;
        this.mTopPileCard = c;
        this.moveStatus = tempCard;
    }

    public Card topCardReturn(){
        return Actions.peek(mActiveDeck);//mActiveDeck.cardDeck.get(0);
    }

    public int deckNumberReturn(){
        return mActiveDeck.returnSize();
        }
    public int playerNumberReturn(){
        return mPlayers.size();
    }

    public static void wrongPlaceSound() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        File file = new File("wrongSound.wav");
        AudioInputStream audiStream = AudioSystem.getAudioInputStream(file);
        Clip clip = AudioSystem.getClip();
        clip.open(audiStream);
        clip.start();
    }
    public String execute(Card cardFromTop, Actions.ACTIONS action, Card gamePileTopCard) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        if( cardFromTop == null ) {
            // no card selected
            wrongPlaceSound();
            return "invalid move";
        }

        resetStatus();
        if (cardFromTop.getColor() == Card.SPECIAL_COLOR && action == Actions.ACTIONS.PLACE) {

            if (cardFromTop.getCardNum() == Card.REVERSE_CARD) {
                action = Actions.ACTIONS.REVERSE;
            } else if (cardFromTop.getCardNum() == Card.SKIP_CARD) {
                action = Actions.ACTIONS.SKIP;
            } else if (cardFromTop.getCardNum() == Card.DRAW_TWO_CARD) {
                action = Actions.ACTIONS.DRAWTWO;
            } else if (cardFromTop.getCardNum() == Card.WILD_CARD){
                action = Actions.ACTIONS.WILDCARD;
            }
        }


        // Skip card case
        if (action == Actions.ACTIONS.SKIP){
            Actions.doAction(action, mActivePlayer, 2, mActiveDeck, cardFromTop, gamePileTopCard);

            if(mActivePlayer.getHand().isEmpty()) {
                return "Winner";
            }

            // Skips to next player
            round++;
            mActivePlayer = mPlayers.get(round % mPlayers.size());
            nextPlayer = mPlayers.get((round + 1) % mPlayers.size());
            prevPlayer = mPlayers.get((round - 1) % mPlayers.size());
            String skippedPlayer = mActivePlayer.getName();
            // skips their turn
            round++;
            mActivePlayer = mPlayers.get(round % mPlayers.size());
            nextPlayer = mPlayers.get((round + 1) % mPlayers.size());
            prevPlayer = mPlayers.get((round - 1) % mPlayers.size());


            return "skipping player " + skippedPlayer;

        }

        // Wild card case
        if (action == Actions.ACTIONS.WILDCARD){
            Actions.doAction(action, mActivePlayer, 2, mActiveDeck, cardFromTop, gamePileTopCard);

            if(mActivePlayer.getHand().isEmpty()) {
                return "Winner";
            }

            return "Place a card of choice";

        }

        // Reverse card case
        if (action == Actions.ACTIONS.REVERSE){
            moveStatus = Actions.doAction(action, mActivePlayer, 2, mActiveDeck, cardFromTop, gamePileTopCard);

            // reverse players
            Collections.reverse(mPlayers);

            String tempPlayer = mActivePlayer.getName();

            mActivePlayer = mPlayers.get(round % mPlayers.size());
            nextPlayer = mPlayers.get((round + 1) % mPlayers.size());
            prevPlayer = mPlayers.get((round - 1) % mPlayers.size());

            return tempPlayer + " has used reverse!";


        }

        // draw two case
        if (action == Actions.ACTIONS.DRAWTWO){

            // place down draw two card
            action = Actions.ACTIONS.PLACE;
            moveStatus = Actions.doAction(action, mActivePlayer, 1 , mActiveDeck, cardFromTop, gamePileTopCard);


            // switch to other player
            String activePlayerTemp = mActivePlayer.name;
            round++;
            mActivePlayer = mPlayers.get(round % mPlayers.size());
            nextPlayer = mPlayers.get((round + 1) % mPlayers.size());
            prevPlayer = mPlayers.get((round - 1) % mPlayers.size());

            // draws 2 for player
            action = Actions.ACTIONS.DRAWTWO;
            Actions.doAction(action, mActivePlayer, 2, mActiveDeck, cardFromTop, gamePileTopCard);

            System.out.println(round + "\n" + mActivePlayer.name + " card " + cardFromTop.getDescription());


            return activePlayerTemp + " used draw 2 cards";

        }

        // draw case
        if (action == Actions.ACTIONS.DRAW) {
            moveStatus = Actions.doAction(action, mActivePlayer, 1, mActiveDeck, cardFromTop, gamePileTopCard);
            System.out.println(round + "\n" + mActivePlayer.name + " card " + cardFromTop.getDescription());

            String activePlayerTemp = mActivePlayer.name;
            
            round++;
            mActivePlayer = mPlayers.get(round % mPlayers.size());
            nextPlayer = mPlayers.get((round + 1) % mPlayers.size());
            prevPlayer = mPlayers.get((round - 1) % mPlayers.size());

            return activePlayerTemp + " has drawn card";
        }
        // place case
        if (action == Actions.ACTIONS.PLACE){

            moveStatus = Actions.doAction(action, mActivePlayer, 1 , mActiveDeck, cardFromTop, gamePileTopCard);

            if (moveStatus.getSuccess() == false) {
                round = round - 1;
                return "invalid move";
            }
            System.out.println(round + "\n" + mActivePlayer.name + " card " + cardFromTop.getDescription());

            if(mActivePlayer.getHand().isEmpty()) {
                return "Winner";
            }

            round++;
            String activePlayerTemp = mActivePlayer.name;
            mActivePlayer = mPlayers.get(round % mPlayers.size());
            nextPlayer = mPlayers.get((round + 1) % mPlayers.size());
            prevPlayer = mPlayers.get((round - 1) % mPlayers.size());
            return activePlayerTemp + " has placed card";
        }
        if(mActivePlayer.getHand().isEmpty()) {
            return "Winner";
        }
        if(mActiveDeck.cardDeck.isEmpty()){
            mActiveDeck = Actions.newDeck();
        }


        return "error";
    }

    public void setDeck(Deck d){
        mActiveDeck = d;
    }

    public int getRound(){
        return round;
    }

    public Player getActivePlayer(){
        return mActivePlayer;
    }

    public Player getNextPlayer() {
        return nextPlayer;
    }

    public Player getPrevPlayer() {
        return prevPlayer;
    }

    public ArrayList getPlayers(){
        return mPlayers;
    }

    public Card getResultCard() {return resultCard;}

    public void resetStatus() {
        moveStatus.setSuccess(true);
    }

    public boolean getStatus() {
        return moveStatus.getSuccess();
    }

}
