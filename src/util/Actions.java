package util;

public class Actions {

    public static enum ACTIONS {DRAW, PLACE, SKIP, REVERSE, DRAWTWO, WILDCARD}
    public static Card mTopCard;

    public static Card pop(Deck d){
        Card result = d.cardDeck.get(0);
        d.cardDeck.remove(0);
        return result;
    }

    public static Card peek(Deck d){
        Card result = d.cardDeck.get(0);
        return result;
    }

    public static Card push(Player p, Card selected){
        int index = 0;
        System.out.println(p.getName());
        /* iterates through the selected player hand and checks
        if the there is a card in that matches the selected card in color and number
        */
        for(int i = 0; i < p.getSize(); i++) {
            if (p.getHand().get(i).getColor() == selected.getColor()) {
                if (p.getHand().get(i).getCardNum() == selected.getCardNum()) {
                    index = i;
                    break;
                }
            }
        }
        mTopCard = p.getHand().get(index);
        p.removeCard(selected);
        return mTopCard;
    }

    public static Deck newDeck(){
        Deck test = new Deck(); //Create deck
        //System.out.println(test.cardDeck);
        test.shuffle();
        //System.out.println(test.cardDeck);
        return test;
    }

    public static Card doAction(ACTIONS choice, Player p, int num, Deck d, Card selectedCard, Card topCard){
        switch(choice){
            case DRAW:
                for(int i = 0; i < num; i++){
                    Card drawnCard = pop(d);
                    p.addCard(drawnCard);
                }
                return selectedCard;
            case PLACE:
                if( selectedCard == null ) {
                    // no card selected;
                    return selectedCard;
                }

                if(topCard.getColor() == Card.SPECIAL_COLOR){
                    push(p, selectedCard);
                    return selectedCard;

                }

                if(topCard.getColor() != selectedCard.getColor()){ //Check if card is unable to be played
                    if(selectedCard.getCardNum() == 10 ||selectedCard.getCardNum() == 11 || selectedCard.getCardNum() == 12 || selectedCard.getCardNum() == 13 ){
                        push(p, selectedCard);
                        return selectedCard;}

                    if(topCard.getCardNum() != selectedCard.getCardNum()){
                        selectedCard.setSuccess(false);
                        return selectedCard;
                    }
                }
                push(p, selectedCard);
                return selectedCard;
            case SKIP:
                push(p, selectedCard);
                return selectedCard;

            case REVERSE:
                push(p, selectedCard);
                return selectedCard;

            case DRAWTWO:
                for(int i = 0; i < num; i++){
                    Card drawnCard = pop(d);
                    p.addCard(drawnCard);
                }
                return selectedCard;

            case WILDCARD:
                push(p, selectedCard);
                return selectedCard;










            default:
                throw new IllegalStateException("Unexpected value: " + choice);
        }


    }
}
