import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


@SuppressWarnings("serial")
public class MainMenu extends JFrame implements ActionListener{
	private String userName = Start.getUser(); //fetching username from the Start class
	private JFrame f;
	private JButton exit, map, statistics, changePassword;
	JLabel backgroundLabel, titleLabel, authors, signedInAs;
//	Font buttonFont, biggerFont;
	Font textFont = new Font("Verdana", Font.BOLD, 14); //declaring font
	boolean changedPass;

	
	public MainMenu() throws FontFormatException, IOException, LineUnavailableException, UnsupportedAudioFileException {
//		background = new ImageIcon(getClass().getResource("/Backgrounds/mainMenuBackground.jpg")); // fetching background image
//		buttonFont = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/textFont.TTF")); //fetching font
//		biggerFont = buttonFont.deriveFont(40f); //deriving font into bigger size
//		changedPass = false; //initializing boolean
		f = new JFrame("Student Misbehaviour Tracker Program"); //initializing new JFrame
		backgroundLabel = new JLabel(); //initializing new background label
		titleLabel = new JLabel("Student Misbehaviour Tracker Program"); //initializing new title label
		signedInAs = new JLabel("<html><p style=\"width:200px\">"+"Currently signed in as: <br>" + userName +"</p></html>"); //initializing new signedInAs label
		makeLabel(signedInAs);
		signedInAs.setBounds(1100, -10, 210, 100); //setting position of label
		f.setSize(1600, 900); //setting size of frame
		backgroundLabel.setSize(1600, 900); //setting size of label
		titleLabel.setSize(980,60); //setting size of label 
		titleLabel.setBounds(550, 100, 1200, 100); //setting position of label
		makeLabel(titleLabel); titleLabel.setFont(new Font("Verdana", Font.BOLD, 18));
		map = new JButton("Map"); //initializing new button for starting the game
		map.setBounds(600, 300, 300, 60); //setting position of game button
		statistics = new JButton("Statistics"); //initializing new button for the tutorial
		statistics.setBounds(600, 400, 300, 60); //setting position of tutorial button
		exit = new JButton("Exit"); //initializing new button to exit the game
		exit.setBounds(600, 500, 300, 60); //setting position of exit button
		makeButton(map);
		makeButton(statistics);
		makeButton(exit);
		f.setLayout(null);
		f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		f.setVisible(true);
		f.setLocationRelativeTo(null);
		f.add(signedInAs);
		f.add(titleLabel);
		f.add(backgroundLabel);
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == map) {
			new Map();
			f.dispose();
		} else if (e.getSource() == statistics) {
			new Statistics();
			f.dispose();
		} else if (e.getSource() == exit) {
			reprompt();
		} else {
				JOptionPane.showMessageDialog(f, "You've already changed your password!");
			}
		}

	
	public void makeLabel(JLabel l) {
		l.setForeground(Color.black);
		l.setFont(textFont);
	}
	
	public void makeButton(JButton b) {
		b.setForeground(Color.BLACK);
		b.setBackground(Color.WHITE);
		b.addActionListener(this);
		b.setFocusable(false);
		b.setOpaque(false);
		b.setContentAreaFilled(false);
		b.setBorderPainted(true);
		backgroundLabel.add(b);
	}
	
	public void reprompt() {
		int result = JOptionPane.showConfirmDialog(f,
				"Are you sure you want to quit?",
				"Confirm Quit", JOptionPane.YES_NO_CANCEL_OPTION);
		if (result == JOptionPane.YES_OPTION) System.exit(0);
	}

}
