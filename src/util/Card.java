package util;

import java.awt.*;

public class Card {
    public static final Color SPECIAL_COLOR = Color.BLACK;
    public static final Color INACTIVE_COLOR = Color.LIGHT_GRAY;

    //creates an Array of colors of the cards in Uno
    public static final Color[] Colors = { Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, SPECIAL_COLOR };

    public static int REVERSE_CARD = 10;
    public static int DRAW_TWO_CARD = 11;
    public static int SKIP_CARD = 12;
    public static int WILD_CARD = 13;

    public boolean success = true;

    private final Color cardColor;
    private final int cardNum; //0-9, 10 for reverse, 11 for draw 2, 12 for skip, 13 for Wild


    public Card(Color c, int n){
        this.cardColor = c;
        this.cardNum = n;
    }

    public int getCardNum() {
        return cardNum;
    }

    public Color getColor() {
        return cardColor;
    }

    public boolean isSpecialCard() {
        return (cardNum > 9);
    }

    public boolean isSkipCard() {
        return (cardNum == SKIP_CARD);
    }

    public boolean isWildCard() {
        return (cardNum == WILD_CARD);
    }

    public boolean isReverseCard() {
        return (cardNum == REVERSE_CARD);
    }

    public boolean isDrawTwoCard() {
        return (cardNum == DRAW_TWO_CARD);
    }

    public boolean getSuccess() { return success; }

    public void setSuccess(boolean b) { this.success = b; }

    public String getLabel() {
       if( cardNum < 0 ) {
           return "";
       }
       if( cardNum <= 9) {
           return String.valueOf(cardNum);
       }

       if( cardNum == SKIP_CARD ) {
           return "S";
       }

        if( cardNum == DRAW_TWO_CARD ) {
            return "D2";
        }

        if( cardNum == WILD_CARD ) {
            return "W";
        }

        if( cardNum == REVERSE_CARD ) {
            return "R";
        }

        return "";
    }

    public String getDescription() {
        return "[" + cardColor.toString() + " : " + getLabel() + "]";
    }
}
