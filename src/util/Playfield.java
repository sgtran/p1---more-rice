package util;

import ui.UnoUI;

import java.util.ArrayList;
import java.util.List;

public class Playfield {
    public int round = 0;
    public Player actPlayer; //active player
    public ArrayList<Player> players = new ArrayList<Player>();
    public static boolean actionSuccess;

    public Playfield(int playerNum, List<String> names){ //Initialize playfield

        Deck deck = Actions.newDeck();
        Card topCard = Actions.pop(deck);
        System.out.println(topCard.getLabel());
        addPlayers(playerNum, names);

        for(int i = 0; i < playerNum; i++){ //Start each player with 7 cards
            for(int j = 0; j < 7; j++){
                players.get(i).addCard(Actions.pop(deck));
            }
        }

        actPlayer = players.get(round % playerNum); //Set first active player
       // topCard = Actions.getmTopCard(); //Set first top card
        UnoUI gameWindow = new UnoUI(actPlayer, topCard, deck, players); //Start game
        gameWindow.setVisible(true); //Make UnoUI visible

    }

    public void addPlayers(int numPlayers, List<String> args) { //Add players to players arraylist
        for(int i = 0; i < numPlayers; i++){
            players.add(new Player(args.get(i)));
        }
    }

}
