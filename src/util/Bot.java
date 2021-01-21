package util;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class Bot extends Player {
    private String name;
    private int green;
    private int yellow;
    private int blue;
    private int red;
    private boolean haveDrawCard;
    static Color[] colorOrder = {Color.GREEN, Color.YELLOW, Color.BLUE, Color.RED};

    public Bot(String newName) {
        super(newName);
    }

    public boolean isBot() {
        return true;
    }

    public int[] countColors() {
        //count number of each color to determine priority (later, higher cards have higher priority)
        for(int i = 0; i < this.getSize(); i++) {
            Card card = this.getHand().get(i);

            if (card.getColor() == Color.GREEN) {
                green++;
            } else if (card.getColor() == Color.YELLOW) {
                yellow++;
            } else if (card.getColor() == Color.BLUE) {
                blue++;
            } else if (card.getColor() == Color.RED) {
                red++;
            } else if (card.getColor() == Color.BLACK) {
                //Used later: if the next player only has 1 card, bot should play draw/skip cards (11 is +2 and 12 is skip)
                if (card.getCardNum() == 11 || card.getCardNum() == 12) {
                    haveDrawCard = true;
                }
            }
        }

        int[] tempArray = {green,yellow,blue,red};
        return tempArray;

    };

    public int[] colorPrioritization() {
        int[] temp = {0,0,0,0};

        //lowest priority for colors is 4 because 1, 2, and 3 are used for +2, reverse, and skip
        int colorPriority = 4;

        int[] colorNum = countColors();

        //assigns priority for each of 4 colors
        for (int i = 0; i < 4; i++) {
            int maxIndex = 0;

            //finds the index for the color with the most cards
            for (int j = 0; j < 3; j++) {
                if (colorNum[j + 1] > colorNum[j]) {
                    maxIndex = j + 1;
                }
            }

            //uses index of most color and sets priority to current colorPriority value
            temp[maxIndex] = colorPriority;

            //colorPriority increases for next highest number
            colorPriority++;

            //ensure same color isn't chosen again
            colorNum[maxIndex] = -1;
        }

        return temp;
    }

    //topcard is mgamepiletopcard
    public ArrayList<Integer> prioritized(Player prevPlayer, Player nextPlayer, Card topCard) {
        ArrayList<Integer> priorities = new ArrayList<Integer>();

        //for each card, add its priority to the arraylist
        for (int i = 0; i < this.getSize(); i++) {
            priorities.add(0);

            Card currentCard = this.getHand().get(i);

            //blacks cards are last resort, so priority 9 (1 priority before drawing a card which is 10)
            if (currentCard.getColor() == Color.BLACK) {
                priorities.set(i,9);
            }

            //check to see the card's color and assign the correct priority
            for(int j = 0; j < 4; j++) {
                if (currentCard.getColor() == colorOrder[j]) {
                    priorities.set(i, colorPrioritization()[j]);
                }
            }

            //if previous player has 1 or 2 cards, prioritize reversing and playing +2 and skip cards
            if (prevPlayer.getSize() <= 2 && currentCard.getCardNum() == 10 && haveDrawCard) {
                priorities.set(i, 3);
            }

            //if next player has 1 or 2 cards, prioritize skip cards
            if (nextPlayer.getSize() <= 2 && currentCard.getCardNum() == 12) {
                priorities.set(i, 2);
            }

            //if next player has 1 or 2 cards, prioritize +2 cards
            if (nextPlayer.getSize() <= 2 && currentCard.getCardNum() == 11) {
                priorities.set(i, 1);
            }

            //if card is not playable, prioritization must be greater than 10
            if (currentCard.getColor() != topCard.getColor() && currentCard.getCardNum() != topCard.getCardNum() && currentCard.getColor() != Card.SPECIAL_COLOR) {
                priorities.set(i, 100);
            }
        }

        return priorities;
    }

    public Card chosenCard(Player prevPlayer, Player nextPlayer, Card topCard) {
        ArrayList<Integer> prioritization = prioritized(prevPlayer, nextPlayer, topCard);

        for (int i = 1; i < 10; i++) {
            for (int j = 0; j < this.getSize(); j++) {
                if (prioritization.get(j) == i) {
                    return this.getHand().get(j);
                }
            }
        }

        return null;
    }

}
