package ui;


import javax.swing.*;
import java.awt.*;

public class GameSettings extends JFrame {

    private JComboBox<String> numPlayersCombo; // Combo box to select number of players
    private JTextArea playerNamesTextField; // Text box for player names
    private static String ENTER = "Enter"; //Submit button
    private JLabel label;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                GameSettings frame = new GameSettings();
                frame.setSize(300,300);
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
    public GameSettings() {
        super("Game Settings");
        setBounds(100, 100, 418, 315);
        Container c = getContentPane();
        JPanel p = new JPanel();
        JButton b = new JButton("Submit"); //Submit button

        // label for changing number of cards
        JLabel deckNumberChangeLabel = new JLabel("Number of Cards in Deck");
        deckNumberChangeLabel.setBounds(30, 30, 100, 100);
        deckNumberChangeLabel.setVisible(true);
        add(deckNumberChangeLabel);

        // label for changing skip settings
        JLabel skipSettingLabel = new JLabel("Skip Settings");
        skipSettingLabel.setBounds(30, 20, 200, 100);
        skipSettingLabel.setVisible(true);
        add(skipSettingLabel);


    }





}
