package util;

import ui.UnoGUI;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Playfield {
    public int round = 0;
    public Player actPlayer; //active player
    public ArrayList<Player> players = new ArrayList<Player>();
    public static boolean actionSuccess;

    public Playfield(int playerNum, List<String> names, int botNum) throws UnsupportedAudioFileException, IOException, LineUnavailableException { //Initialize playfield

        Deck deck = Actions.newDeck();
        Card topCard = Actions.pop(deck);
        System.out.println(topCard.getLabel());
        addPlayers(playerNum, names, botNum);

        for(int i = 0; i < playerNum + botNum; i++){ //Start each player with 7 cards
            for(int j = 0; j < 7; j++){
                players.get(i).addCard(Actions.pop(deck));
            }
        }

        actPlayer = players.get(round % playerNum); //Set first active player
       // topCard = Actions.getmTopCard(); //Set first top card
        UnoGUI gameWindow = new UnoGUI(actPlayer, topCard, deck, players); //Start game
        gameWindow.setVisible(true); //Make UnoUI visible

    }

    public void addPlayers(int numPlayers, List<String> args, int botNum) { //Add players to players arraylist

        Random random = new Random();

        //location of bots
        int[] botPositions = new int[botNum];
        //chooses a random location for each bot
        for(int i = 0; i < botNum; i++) {
            botPositions[i] = random.nextInt(numPlayers);
        }

        for(int i = 0; i < numPlayers; i++){
            players.add(new Player(args.get(i)));

            //if there's a bot at position i, then a bot is added
            for(int j = 0; j < botNum; j++) {
                if(botPositions[j] == i) {
                    String botName = "Bot" + Integer.toString(j+1);
                    players.add(new Bot(botName));
                }
            }
        }
    }

}
