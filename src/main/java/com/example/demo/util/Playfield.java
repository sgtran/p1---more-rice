package com.example.demo.util;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import com.example.demo.util.Actions.ACTIONS;


import static com.example.demo.util.Actions.*;
import static com.example.demo.util.Actions.pop;

public class Playfield {
    public int round = 0;
    public Player actPlayer; //active player
    public ArrayList<Player> players = new ArrayList<Player>();
    public static boolean actionSuccess;
    private Deck deck;
    private Card topCard;
    private Player nextPlayer;
    private Player prevPlayer;
    boolean winFlag;
    private Card resultCard;
    private Card moveStatus;
    private Card tempCard = new Card(Color.BLUE, 2);

    public Playfield(int playerNum, List<String> names) throws IOException, LineUnavailableException { //Initialize playfield

        deck = newDeck();
        topCard = pop(deck);
        System.out.println(topCard.getLabel());
        addPlayers(playerNum, names, 0);

        for(int i = 0; i < playerNum + 0; i++){ //Start each player with 7 cards
            for(int j = 0; j < 7; j++){
                players.get(i).addCard(pop(deck));
            }
        }

        actPlayer = players.get(round % playerNum); //Set first active player
       // topCard = Actions.getmTopCard(); //Set first top card
        /*
        UnoGUI gameWindow = new UnoGUI(actPlayer, topCard, deck, players); //Start game
        gameWindow.setVisible(true); //Make UnoUI visible
         */
        this.round = 0;
        this.actPlayer = players.get(0);
        this.nextPlayer = players.get(1);
        this.prevPlayer = players.get(players.size() - 1);
        this.moveStatus = tempCard;

    }

    public void addPlayers(int nuplayers, List<String> args, int botNum) { //Add players to players arraylist

        Random random = new Random();

        //location of bots
        int[] botPositions = new int[botNum];
        //chooses a random location for each bot
        for(int i = 0; i < botNum; i++) {
            botPositions[i] = random.nextInt(nuplayers);
        }

        for(int i = 0; i < nuplayers; i++){
            players.add(new Player(args.get(i)));

            //if there's a bot at position i, then a bot is added
            for(int j = 0; j < botNum; j++) {
                if(botPositions[j] == i) {
                    String botName = "Bot" + (j + 1);
                    players.add(new Bot(botName));
                }
            }
        }
    }

    public Playfield execute(ACTIONS action, int cardIndex) throws UnsupportedAudioFileException, IOException, LineUnavailableException
    {
        Card selectedCard = actPlayer.getHand().get(cardIndex);

        if( topCard == null ) {
            // no card selected
            return this;
        }

        if (selectedCard.getColor() == Card.SPECIAL_COLOR && action == ACTIONS.PLACE) {

            if (selectedCard.getCardNum() == Card.REVERSE_CARD) {
                action = ACTIONS.REVERSE;
            } else if (selectedCard.getCardNum() == Card.SKIP_CARD) {
                action = ACTIONS.SKIP;
            } else if (selectedCard.getCardNum() == Card.DRAW_TWO_CARD) {
                action = ACTIONS.DRAWTWO;
            } else if (selectedCard.getCardNum() == Card.WILD_CARD){
                action = ACTIONS.WILDCARD;
            }
        }


        // Skip card case
            if (action == ACTIONS.SKIP){
                doAction(action, actPlayer, 2, deck, selectedCard, topCard);

            if(actPlayer.getHand().isEmpty()) {
                win(actPlayer);
                return this;
            }

            // Skips to next player
            round++;
            actPlayer = players.get(round % players.size());
            nextPlayer = players.get((round + 1) % players.size());
            prevPlayer = players.get((round - 1) % players.size());
            String skippedPlayer = actPlayer.getName();
            // skips their turn
            round++;
            actPlayer = players.get(round % players.size());
            nextPlayer = players.get((round + 1) % players.size());
            prevPlayer = players.get((round - 1) % players.size());


            return this;

        }

        // Wild card case
        if (action == ACTIONS.WILDCARD){
            doAction(action, actPlayer, 2, deck, selectedCard, topCard);

            if(actPlayer.getHand().isEmpty()) {
                winFlag = true;
                win(actPlayer);
                return this;

            }

            return this;

        }

        // Reverse card case
        if (action == ACTIONS.REVERSE){
            moveStatus = doAction(action, actPlayer, 2, deck, topCard, topCard);

            // reverse players
            Collections.reverse(players);

            String tempPlayer = actPlayer.getName();

            actPlayer = players.get(round % players.size());
            nextPlayer = players.get((round + 1) % players.size());
            prevPlayer = players.get((round - 1) % players.size());

            return this;


        }

        // draw two case
        if (action == ACTIONS.DRAWTWO){

            // place down draw two card
            action = ACTIONS.PLACE;
            moveStatus = doAction(action, actPlayer, 1 , deck, selectedCard, topCard);


            // switch to other player
            String activePlayerTemp = actPlayer.name;
            round++;
            actPlayer = players.get(round % players.size());
            nextPlayer = players.get((round + 1) % players.size());
            prevPlayer = players.get((round - 1) % players.size());

            // draws 2 for player
            action = ACTIONS.DRAWTWO;
            doAction(action, actPlayer, 2, deck, selectedCard, topCard);

            System.out.println(round + "\n" + actPlayer.name + " card " + topCard.getDescription());


            return this;

        }

        // draw case
        if (action == ACTIONS.DRAW) {
            moveStatus = doAction(action, actPlayer, 1, deck, selectedCard, topCard);
            System.out.println(round + "\n" + actPlayer.name + " card " + topCard.getDescription());

            String activePlayerTemp = actPlayer.name;

            round++;
            actPlayer = players.get(round % players.size());
            nextPlayer = players.get((round + 1) % players.size());
            prevPlayer = players.get((round - 1) % players.size());

            return this;
        }
        // place case
        if (action == ACTIONS.PLACE){

            System.out.println("selected card "+ selectedCard.getCardNum() + selectedCard.getColorSanitized());
            topCard = doAction(action, actPlayer, 1 , deck, selectedCard, topCard);
            System.out.println(mTopCard);

            System.out.println(round + "\n" + actPlayer.name + " card " + topCard.getDescription());

            if(actPlayer.getHand().isEmpty()) {
                win(actPlayer);
                return this;

            }

            round++;
            actPlayer = players.get(round % players.size());
            nextPlayer = players.get((round + 1) % players.size());
            prevPlayer = players.get((round - 1) % players.size());
            return this;
        }
        if(actPlayer.getHand().isEmpty()) {
            win(actPlayer);
            return this;

        }
        if(deck.getCardDeck().isEmpty()){
            deck = newDeck();
        }


        return this;
    }

    public Player getCurrentPlayer(){
        return actPlayer;
    }

    public Card getTopCard(){
        return topCard;
    }

    public void resetStatus() {
        moveStatus.setSuccess(true);
    }

    public void win(Player winner) {
        for (int i = 0; i < players.get(i).getSize(); i++) {

            Player tempPlayer = players.get(i);

            for (int j = 0; j < tempPlayer.getSize(); j++) {

                int cardNum = tempPlayer.getHand().size();

                if (cardNum < 10) {
                    winner.addScore(cardNum);
                } else if (cardNum == 10) {
                    winner.addScore(15);
                } else if (cardNum == 11) {
                    winner.addScore(20);
                } else if (cardNum == 10) {
                    winner.addScore(30);
                } else if (cardNum == 10) {
                    winner.addScore(40);
                }

            }
        }


    }
    

}
