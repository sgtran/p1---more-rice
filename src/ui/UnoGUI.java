package ui;

import util.Actions.ACTIONS;
import util.Card;
import util.Deck;
import util.Player;
import util.SpecialActions;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class UnoGUI extends JFrame {

    private JPanel mainPanel;
    private JPanel gamePanel;
    private JPanel cardsPanel;
    private JButton button1;
    private JButton button7;
    private JButton button6;
    private JButton button4;
    private JButton button3;
    private JButton button8;
    private JButton button9;
    private JButton button2;
    private JButton button10;
    private JButton button5;
    private JButton mDrawCardButton;
    private JButton mPlaceCardButton;
    private JButton mTopCardButton;
    private JLabel mCurrentPlayerLabel;
    private JButton mPileButton;
    private JLabel mGamePile;
    private JButton mGameSettingsButton;
    private Player placeholder = new Player("placeholder");
    private JFrame message = new JFrame();
    private JOptionPane optionpane = new JOptionPane();

    private ACTIONS action;

    private Player mActivePlayer = new Player("test");
    private Player nextPlayer = new Player("next test");
    private Player prevPlayer = new Player("prev test");
    public int round = 0;
    private ArrayList<Player> mPlayers = new ArrayList<Player>();
    private Card mTopOfDeckCard;
    private ArrayList<JButton> mCardButtons;
    public SpecialActions model;
    private static final Color DEFAULT_COLOR = Color.white;
    private Card selectedCard;
    public Card mGamePileTopCard;

    /**
     * Launch the application.
     */

    public void specialAction(Card cardFromTop) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        while (true) {
            String output = model.execute(cardFromTop, action, mGamePileTopCard);

            System.out.println(model.getStatus());
           // message.setLayout(null);
           // message.setAlwaysOnTop(true);
           // message.setBounds(250, 200, 0, 0);
           // message.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
           // message.setVisible(true);
            if (output.equalsIgnoreCase("invalid move")) {
            //    optionpane.showMessageDialog(message, output);
                JOptionPane.showMessageDialog(null, output);
                round = model.getRound();
                mActivePlayer = model.getActivePlayer();
                nextPlayer = model.getNextPlayer();
                prevPlayer = model.getPrevPlayer();
                updateActivePlayer(mActivePlayer);
                break;
            }

            if (output.equalsIgnoreCase("Winner")) {
                winnerSound();
               // JOptionPane.showMessageDialog(message, output);
                JOptionPane.showMessageDialog(null, output);
                this.setVisible(false);
                this.dispose();
                break;
            }

           // JOptionPane.showMessageDialog(message, output);
            JOptionPane.showMessageDialog(null, output);


            round = model.getRound();
            mActivePlayer = model.getActivePlayer();
            nextPlayer = model.getNextPlayer();
            prevPlayer = model.getPrevPlayer();
            updateGamePile(selectedCard);
            updateActivePlayer(mActivePlayer);

            // Reset
            selectedCard = null;
            //message.setVisible(false);
            break;
        }
    }

    public UnoGUI(Player currentPlayer, Card topCard, Deck deck, ArrayList<Player> players) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        model = new SpecialActions(deck, players, topCard);
        System.out.println("test");
        createUIComponents();

        // update pile
        updateGamePile(topCard);

        updateTopCard();

        // Display active player(p)'s hand
        updateActivePlayer(currentPlayer);
        System.out.println(mActivePlayer);
        // Handle the draw card action and handle the
        // place card actions
        mDrawCardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    drawCardClick();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (UnsupportedAudioFileException unsupportedAudioFileException) {
                    unsupportedAudioFileException.printStackTrace();
                } catch (LineUnavailableException lineUnavailableException) {
                    lineUnavailableException.printStackTrace();
                }
                action = ACTIONS.DRAW;
                updateTopCard();
                try {
                    specialAction(mTopOfDeckCard);
                } catch (UnsupportedAudioFileException unsupportedAudioFileException) {
                    unsupportedAudioFileException.printStackTrace();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (LineUnavailableException lineUnavailableException) {
                    lineUnavailableException.printStackTrace();
                }

                updateTopCard();

            }
        });

        UnoGUI me = this;

        mGameSettingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                GameSettings frame = null;
                try {
                    gameSettingClick();
                    frame = new GameSettings(me);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (UnsupportedAudioFileException unsupportedAudioFileException) {
                    unsupportedAudioFileException.printStackTrace();
                } catch (LineUnavailableException lineUnavailableException) {
                    lineUnavailableException.printStackTrace();
                }
                frame.setSize(300, 300);
                frame.setVisible(true);


            }
        });

        mPlaceCardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                action = ACTIONS.PLACE;

                mCurrentPlayerLabel.setText(mActivePlayer.name);
                try {
                    specialAction(selectedCard);
                } catch (UnsupportedAudioFileException unsupportedAudioFileException) {
                    unsupportedAudioFileException.printStackTrace();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (LineUnavailableException lineUnavailableException) {
                    lineUnavailableException.printStackTrace();
                }

            }


        });

    }

    public static void gameSettingClick() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        File file = new File("gameSettingClick.wav");
        AudioInputStream audiStream = AudioSystem.getAudioInputStream(file);
        Clip clip = AudioSystem.getClip();
        clip.open(audiStream);
        clip.start();
    }

    public static void drawCardClick() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        File file = new File("swoop3.wav");
        AudioInputStream audiStream = AudioSystem.getAudioInputStream(file);
        Clip clip = AudioSystem.getClip();
        clip.open(audiStream);
        clip.start();
    }

    public static void winnerSound() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        File file = new File("winner2.wav");
        AudioInputStream audiStream = AudioSystem.getAudioInputStream(file);
        Clip clip = AudioSystem.getClip();
        clip.open(audiStream);
        clip.start();
    }


    //method is used for player objects
    //method purpose is to update current active player's hand and name
    private void updateActivePlayer(Player p) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        mActivePlayer = p;

        // First clear and reset the current hand
        // to update the cards view
        clearHand();

        for (int i = 0; i < mActivePlayer.getHand().size(); ++i) {
            Card card = mActivePlayer.getHand().get(i);
            JButton cardButton = mCardButtons.get(i);

            // update the card display

            cardButton.setEnabled(true);
            cardButton.setVisible(true);
            cardButton.setBackground(card.getColor());
            cardButton.setText(card.getLabel());

            // Update text color
            cardButton.setForeground(card.isSpecialCard() ? Color.white : Color.black);


        }

        updateCurrentPlayerLabel();
        System.out.println(mActivePlayer.getHand());

        if (mActivePlayer.name.length() >= 3) {

            boolean isBot = false;

            if (mActivePlayer.isBot()) {
                System.out.println("bot");
                botPlay(mActivePlayer);
                mActivePlayer = model.getActivePlayer();
                nextPlayer = model.getNextPlayer();
                prevPlayer = model.getPrevPlayer();
                updateActivePlayer(mActivePlayer);
            }
        }
    }

    private void updateCurrentPlayerLabel() {
        mCurrentPlayerLabel.setText(mActivePlayer.name);
    }

    private void updateTopCard() {
        mTopOfDeckCard = model.topCardReturn();

        if (mTopOfDeckCard == null) {
            mTopCardButton.setBackground(Card.INACTIVE_COLOR);
            mTopCardButton.setText("");
            return;
        }

        mTopCardButton.setText(mTopOfDeckCard.getLabel());
        mTopCardButton.setBackground(mTopOfDeckCard.getColor());

        // Update text color
        mTopCardButton.setForeground(mTopOfDeckCard.isSpecialCard() ? Color.white : Color.black);
    }

    private void updateGamePile(Card c) {
        if (c == null) {
            return;
        }

        mGamePileTopCard = c;

        mPileButton.setText(c.getLabel());
        mPileButton.setBackground(c.getColor());
        mPileButton.setForeground(c.isSpecialCard() ? Color.white : Color.black);
    }

    public void invalid() {
        JOptionPane.showMessageDialog(null, "Invalid move");
    }


    private void createUIComponents() {
        getContentPane().setBackground(new Color(250, 200, 250));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 500, 400);
        setContentPane(mainPanel);

        mCardButtons = new ArrayList<>();

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedCard = mActivePlayer.getHand().get(0);
            }
        });
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedCard = mActivePlayer.getHand().get(1);
            }
        });
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedCard = mActivePlayer.getHand().get(2);
            }
        });
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedCard = mActivePlayer.getHand().get(3);
            }
        });
        button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedCard = mActivePlayer.getHand().get(4);
            }
        });
        button6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedCard = mActivePlayer.getHand().get(5);
            }
        });
        button7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedCard = mActivePlayer.getHand().get(6);
            }
        });
        button8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedCard = mActivePlayer.getHand().get(7);
            }
        });
        button9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedCard = mActivePlayer.getHand().get(8);
            }
        });
        button10.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedCard = mActivePlayer.getHand().get(9);
            }
        });


        mCardButtons.add(button1);
        mCardButtons.add(button2);
        mCardButtons.add(button3);
        mCardButtons.add(button4);
        mCardButtons.add(button5);
        mCardButtons.add(button6);
        mCardButtons.add(button7);
        mCardButtons.add(button8);
        mCardButtons.add(button9);
        mCardButtons.add(button10);

        clearHand();

        // Set top deck as useless card.
        // It should not be clickable.
        mTopCardButton.setEnabled(false);
        mTopCardButton.setVisible(true);
        mTopCardButton.setBackground(Card.INACTIVE_COLOR);
        mTopCardButton.setText("");

        mPileButton.setEnabled(false);
        mPileButton.setVisible(true);
        mPileButton.setBackground(Card.INACTIVE_COLOR);
        mPileButton.setText("");

        mCurrentPlayerLabel.setText("");
    }

    // this function plays music

    private void clearHand() {
        // Hide and disable card buttons
        for (JButton button : mCardButtons) {
            button.setEnabled(false);
            button.setVisible(false);
            button.setText("");
            button.setBackground(DEFAULT_COLOR);
        }
    }

    private void botPlay(Player p) throws UnsupportedAudioFileException, IOException, LineUnavailableException {

        selectedCard = p.chosenCard(prevPlayer,nextPlayer,mGamePileTopCard);

        if(selectedCard != null) {

            action = ACTIONS.PLACE;

            mCurrentPlayerLabel.setText(mActivePlayer.name);
            try {
                specialAction(selectedCard);
            } catch (UnsupportedAudioFileException unsupportedAudioFileException) {
                unsupportedAudioFileException.printStackTrace();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            } catch (LineUnavailableException lineUnavailableException) {
                lineUnavailableException.printStackTrace();
            }

        } else {

            try {
                drawCardClick();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            } catch (UnsupportedAudioFileException unsupportedAudioFileException) {
                unsupportedAudioFileException.printStackTrace();
            } catch (LineUnavailableException lineUnavailableException) {
                lineUnavailableException.printStackTrace();
            }
            action = ACTIONS.DRAW;
            updateTopCard();
            try {
                specialAction(mTopOfDeckCard);
            } catch (UnsupportedAudioFileException unsupportedAudioFileException) {
                unsupportedAudioFileException.printStackTrace();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            } catch (LineUnavailableException lineUnavailableException) {
                lineUnavailableException.printStackTrace();
            }

            updateTopCard();

        }

    }

}
