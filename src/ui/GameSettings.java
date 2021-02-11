package ui;


import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;


public class GameSettings extends JFrame {

    private JComboBox<String> numPlayersCombo; // Combo box to select number of players
    private JTextArea playerNamesTextField; // Text box for player names
    private static final String ENTER = "Enter"; //Submit button
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

    // this function plays music
    public void music() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        File file = new File("gameMusic.wav");
        AudioInputStream audiStream = AudioSystem.getAudioInputStream(file);
        Clip clip = AudioSystem.getClip();
        clip.open(audiStream);
        clip.start();
    }

    public void click() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        File file = new File("click.wav");
        AudioInputStream audiStream = AudioSystem.getAudioInputStream(file);
        Clip clip = AudioSystem.getClip();
        clip.open(audiStream);
        clip.start();

    }
    public GameSettings(UnoGUI unoui) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
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
        JButton colorSettingButton = new JButton("Color Settings");
        colorSettingButton.setBounds(10, 70, 200, 20);
        colorSettingButton.setVisible(true);
        panel.add(colorSettingButton);

        colorSettingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    click();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (UnsupportedAudioFileException unsupportedAudioFileException) {
                    unsupportedAudioFileException.printStackTrace();
                } catch (LineUnavailableException lineUnavailableException) {
                    lineUnavailableException.printStackTrace();
                }

            }
        });


        // button for removing special cards
        JButton removeSpecialCardsButton = new JButton("Remove all special cards");
        removeSpecialCardsButton.setBounds(10, 100, 200, 20);
        removeSpecialCardsButton.setVisible(true);
        panel.add(removeSpecialCardsButton);

        removeSpecialCardsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    click();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (UnsupportedAudioFileException unsupportedAudioFileException) {
                    unsupportedAudioFileException.printStackTrace();
                } catch (LineUnavailableException lineUnavailableException) {
                    lineUnavailableException.printStackTrace();
                }

            }
        });

        // button for quit game
        JButton quitGameButton = new JButton("Quit Game");
        quitGameButton.setBounds(10, 130, 200, 20);
        quitGameButton.setVisible(true);
        panel.add(quitGameButton);

        quitGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    click();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (UnsupportedAudioFileException unsupportedAudioFileException) {
                    unsupportedAudioFileException.printStackTrace();
                } catch (LineUnavailableException lineUnavailableException) {
                    lineUnavailableException.printStackTrace();
                }
                unoui.dispose();
                System.exit(0);
            }
        });

        // button for music
        JButton musicButton = new JButton("Cool Music");
        musicButton.setBounds(10, 160, 200, 20);
        musicButton.setVisible(true);
        panel.add(musicButton);

        musicButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    music();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (UnsupportedAudioFileException unsupportedAudioFileException) {
                    unsupportedAudioFileException.printStackTrace();
                } catch (LineUnavailableException lineUnavailableException) {
                    lineUnavailableException.printStackTrace();
                }
            }
        });

        this.add(panel);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setSize(500, 500);
        this.setVisible(true);

    }





}
