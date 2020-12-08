package ui;

import util.Playfield;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Uno extends JFrame {

    private JComboBox<String> numPlayersCombo; // Combo box to select number of players
    private JTextArea playerNamesTextField; // Text box for player names
    private static String ENTER = "Enter"; //Submit button
    private JLabel label;

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

        String[] numPlayersChoice =  { "2", "3", "4", "5", "6", "7", "8", "9", "10" };

        numPlayersCombo = new JComboBox<String>(numPlayersChoice); //Initial setting for # of players
        p.add(numPlayersCombo); //Add text area for year to panel
        label = new JLabel();
        label.setText("Names of each player (Separated by comma)");
        p.add(label);
        playerNamesTextField = new JTextArea("",1, 10);//Initial setting for names of players separated by commas
        p.add(playerNamesTextField); //Add text area for season to panel
        p.add(b); //Add button to panel
        c.add(p); //add panel to container

        b.addActionListener(e -> {
            int tempNum = Integer.parseInt(numPlayersCombo.getSelectedItem().toString());
            Matcher m = Pattern.compile("[^,]+")
                    .matcher(playerNamesTextField.getText());
            while (m.find()) {
                allMatches.add(m.group());
            }
            new Playfield(tempNum, allMatches);
        });

    }
}
