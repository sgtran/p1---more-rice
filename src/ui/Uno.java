package ui;

import util.Playfield;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Uno extends JFrame {

    private JLabel label1; // Label for player # combo box
    private JComboBox<String> numPlayersCombo; // Combo box to select number of players
    private JLabel label2; // Label for player names text field
    private JTextArea playerNamesTextField; // Text box for player names
    private JLabel label3; // Label for bot # combo box
    private JComboBox<String> numBotsCombo; // Combo box to select number of bots
    private static String ENTER = "Enter"; //Submit button

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Uno frame = new Uno();
                frame.setSize(300,300);
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Uno(){
        super("Uno");
        setBounds(100, 100, 418, 315);
        Container c = getContentPane();
        JPanel p = new JPanel();
        JButton b = new JButton("Submit"); //Submit button
        List<String> allMatches = new ArrayList<String>();

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        String[] numPlayersChoice =  { "2", "3", "4", "5", "6"};
        String[] numBotsChoice = {"0", "1", "2"};

        label1 = new JLabel();
        label1.setText("How many players?");
        p.add(label1);
        numPlayersCombo = new JComboBox<String>(numPlayersChoice); //Initial setting for # of players
        p.add(numPlayersCombo); //Add text area for year to panel
        label2 = new JLabel();
        label2.setText("Names of each player (Separated by comma)");
        p.add(label2);
        playerNamesTextField = new JTextArea("",1, 10);//Initial setting for names of players separated by commas
        p.add(playerNamesTextField); //Add text area for season to panel
        label3 = new JLabel();
        label3.setText("How many bots?");
        p.add(label3);
        numBotsCombo = new JComboBox<String>(numBotsChoice); //Initial setting for # of players
        p.add(numBotsCombo); //Add text area for year to panel
        p.add(b); //Add button to panel
        c.add(p); //add panel to container

        b.addActionListener(e -> {
            int tempNum = Integer.parseInt(numPlayersCombo.getSelectedItem().toString());
            int botNum = Integer.parseInt(numBotsCombo.getSelectedItem().toString());
            Matcher m = Pattern.compile("[^,]+")
                    .matcher(playerNamesTextField.getText());
            while (m.find()) {
                allMatches.add(m.group());
            }
            try {
                new Playfield(tempNum, allMatches, botNum);
            } catch (UnsupportedAudioFileException unsupportedAudioFileException) {
                unsupportedAudioFileException.printStackTrace();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            } catch (LineUnavailableException lineUnavailableException) {
                lineUnavailableException.printStackTrace();
            }
        });
    }
}
