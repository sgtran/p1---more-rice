package ui;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameSettings extends JFrame {

    private JComboBox<String> numPlayersCombo; // Combo box to select number of players
    private JTextArea playerNamesTextField; // Text box for player names
    private static String ENTER = "Enter"; //Submit button
    private JLabel label;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                GameSettings frame = new GameSettings(null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
    public GameSettings(UnoUI unoui) {
        super("Game Settings");
        setBounds(100, 100, 418, 315);
        Container c = getContentPane();
        JPanel panel = new JPanel();
        JButton b = new JButton("Submit"); //Submit button

        panel.setLayout(null);

        // label for changing number of cards
        int deckNumberReturn = (unoui == null ? 99 : unoui.model.deckNumberReturn());
        JLabel deckNumberChangeLabel = new JLabel("Number of Cards in Deck: " + deckNumberReturn);

        //Dimension labelSize = deckNumberChangeLabel.getPreferredSize();
        deckNumberChangeLabel.setBounds(10, 10, 200, 20);
        deckNumberChangeLabel.setVisible(true);
        panel.add(deckNumberChangeLabel);

        // label for number of players
        int playerNumberReturn = (unoui == null ? 99 : unoui.model.playerNumberReturn());
        JLabel playerNumberReturnLabel = new JLabel("Number of Players playing: " + playerNumberReturn);

        //Dimension labelSize = deckNumberChangeLabel.getPreferredSize();
        playerNumberReturnLabel.setBounds(10, 40, 200, 20);
        playerNumberReturnLabel.setVisible(true);
        panel.add( playerNumberReturnLabel);

        // button for changing color settings
        JButton skipSettingButton = new JButton("Color Settings");
        skipSettingButton.setBounds(10, 70, 200, 20);
        skipSettingButton.setVisible(true);
        panel.add(skipSettingButton);

        // button for removing special cards
        JButton removeSpecialCardsButton = new JButton("Remove all special cards");
        removeSpecialCardsButton.setBounds(10, 100, 200, 20);
        removeSpecialCardsButton.setVisible(true);
        panel.add(removeSpecialCardsButton);

        // button for quit game
        JButton quitGameButton = new JButton("Quit Game");
        quitGameButton.setBounds(10, 130, 200, 20);
        quitGameButton.setVisible(true);
        panel.add(quitGameButton);

        quitGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                unoui.dispose();
                System.exit(0);
            }
        });

        this.add(panel);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setSize(500, 500);
        this.setVisible(true);

    }





}
