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

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(3, 7, new Insets(0, 0, 0, 0), -1, -1));
        gamePanel = new JPanel();
        gamePanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(5, 9, new Insets(10, 10, 10, 10), -1, -1));
        mainPanel.add(gamePanel, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 7, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, true));
        final JLabel label1 = new JLabel();
        label1.setText("Current Player:");
        gamePanel.add(label1, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_EAST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        mCurrentPlayerLabel = new JLabel();
        mCurrentPlayerLabel.setText("Sean");
        gamePanel.add(mCurrentPlayerLabel, new com.intellij.uiDesigner.core.GridConstraints(0, 3, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        mDrawCardButton = new JButton();
        mDrawCardButton.setText("Draw Card");
        gamePanel.add(mDrawCardButton, new com.intellij.uiDesigner.core.GridConstraints(2, 1, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        mPlaceCardButton = new JButton();
        mPlaceCardButton.setText("Place Card");
        gamePanel.add(mPlaceCardButton, new com.intellij.uiDesigner.core.GridConstraints(2, 3, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Top Card:");
        gamePanel.add(label2, new com.intellij.uiDesigner.core.GridConstraints(4, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_EAST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer1 = new com.intellij.uiDesigner.core.Spacer();
        gamePanel.add(spacer1, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, new Dimension(-1, 20), null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer2 = new com.intellij.uiDesigner.core.Spacer();
        gamePanel.add(spacer2, new com.intellij.uiDesigner.core.GridConstraints(1, 3, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, new Dimension(-1, 20), null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer3 = new com.intellij.uiDesigner.core.Spacer();
        gamePanel.add(spacer3, new com.intellij.uiDesigner.core.GridConstraints(3, 3, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, new Dimension(-1, 20), null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer4 = new com.intellij.uiDesigner.core.Spacer();
        gamePanel.add(spacer4, new com.intellij.uiDesigner.core.GridConstraints(3, 1, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, new Dimension(-1, 20), null, null, 0, false));
        mTopCardButton = new JButton();
        mTopCardButton.setText("W");
        gamePanel.add(mTopCardButton, new com.intellij.uiDesigner.core.GridConstraints(4, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(50, 50), new Dimension(50, 50), new Dimension(50, 50), 0, false));
        mPileButton = new JButton();
        mPileButton.setText("None");
        gamePanel.add(mPileButton, new com.intellij.uiDesigner.core.GridConstraints(4, 5, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(50, 50), new Dimension(50, 50), null, 0, false));
        mGamePile = new JLabel();
        mGamePile.setText("GamePile");
        gamePanel.add(mGamePile, new com.intellij.uiDesigner.core.GridConstraints(4, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        mGameSettingsButton = new JButton();
        mGameSettingsButton.setBackground(new Color(-16769304));
        mGameSettingsButton.setEnabled(true);
        mGameSettingsButton.setForeground(new Color(-4499935));
        mGameSettingsButton.setText("Game Settings");
        gamePanel.add(mGameSettingsButton, new com.intellij.uiDesigner.core.GridConstraints(2, 6, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        cardsPanel = new JPanel();
        cardsPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 5, new Insets(20, 20, 20, 20), -1, -1));
        mainPanel.add(cardsPanel, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 2, 7, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        button1 = new JButton();
        button1.setText("10");
        cardsPanel.add(button1, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(50, 50), new Dimension(50, 50), new Dimension(50, 50), 0, false));
        button2 = new JButton();
        button2.setBackground(new Color(-4521972));
        button2.setText("Button");
        cardsPanel.add(button2, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(50, 50), new Dimension(50, 50), new Dimension(50, 50), 0, false));
        button6 = new JButton();
        button6.setText("Button");
        cardsPanel.add(button6, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(50, 50), new Dimension(50, 50), new Dimension(50, 50), 0, false));
        button7 = new JButton();
        button7.setText("Button");
        cardsPanel.add(button7, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(50, 50), new Dimension(50, 50), new Dimension(50, 50), 0, false));
        button8 = new JButton();
        button8.setText("Button");
        cardsPanel.add(button8, new com.intellij.uiDesigner.core.GridConstraints(1, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(50, 50), new Dimension(50, 50), new Dimension(50, 50), 0, false));
        button10 = new JButton();
        button10.setText("Button");
        cardsPanel.add(button10, new com.intellij.uiDesigner.core.GridConstraints(1, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(50, 50), new Dimension(50, 50), new Dimension(50, 50), 0, false));
        button4 = new JButton();
        button4.setBackground(new Color(-4736256));
        button4.setText("Button");
        cardsPanel.add(button4, new com.intellij.uiDesigner.core.GridConstraints(0, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(50, 50), new Dimension(50, 50), new Dimension(50, 50), 0, false));
        button3 = new JButton();
        button3.setBackground(new Color(-16729327));
        button3.setText("Button");
        cardsPanel.add(button3, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(50, 50), new Dimension(50, 50), new Dimension(50, 50), 0, false));
        button9 = new JButton();
        button9.setText("Button");
        cardsPanel.add(button9, new com.intellij.uiDesigner.core.GridConstraints(1, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(50, 50), new Dimension(50, 50), new Dimension(50, 50), 0, false));
        button5 = new JButton();
        button5.setText("Button");
        cardsPanel.add(button5, new com.intellij.uiDesigner.core.GridConstraints(0, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(50, 50), new Dimension(50, 50), new Dimension(50, 50), 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }
}
