import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
/**
 * Start method to open new frame for users to register or sign in
 * Date: Feb 12, 2024
 */
public class Start extends JFrame implements ActionListener {
	//declaration of global variables
	private static final long serialVersionUID = 1L;
	private static JFrame f; 
	private JLabel backgroundLabel;
	private JButton login;
	private JButton register;
	private JLabel userLabel, passLabel;
	private JTextArea userName; 
	private JPasswordField password;
	private String fileName = "accounts.txt"; //initializing string
	private BufferedReader in;
	private BufferedWriter out;
	public static ArrayList<UserAccount> accounts = new ArrayList<>(); //initializing string array
	String[] users, passwords; //initializing string arrays
	static int numOfUsers; 
	public static String curUser;
	public static String curPass;
	Font buttonFont = new Font("Comic Sans", Font.BOLD, 14); //initializing new font

	public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
		new Start();
	}

	Start() throws IOException, NoSuchAlgorithmException {
		//initialization of variables
		in = new BufferedReader(new FileReader(fileName)); //instantiating BufferedReader to read in usernames, passwords and scores
		//reading in usernames and passwords 
		if(!in.readLine().equals(""+0)) {
			users = in.readLine().split(" ");
			passwords = in.readLine().split(" ");
			numOfUsers = users.length; 
		} else {
			numOfUsers = 0;
		}
		for(int i = 0; i < numOfUsers; i++) { //manually setting data for the accounts ArrayList 
			accounts.add(new UserAccount(users[i], passwords[i])); 
		} //storing data into 2D String array 
		backgroundLabel = new JLabel(); //instantiating background label
		backgroundLabel.setSize(1280, 972); //setting size
		backgroundLabel.setOpaque(true);
		backgroundLabel.setBackground(Color.gray);
		f = new JFrame("SMTP - Login"); //instantiating frame
		JLabel newPlayer = new JLabel("New to the program? Register now!"); //instantiating JLabel
		newPlayer.setBounds(555, 480, 300, 30); //setting bounds
		backgroundLabel.add(newPlayer); //adding label to background label
		userName = new JTextArea(); //instantiating username text area
		userName.setBounds(500, 355, 300, 20); //setting bounds
		userLabel = new JLabel("Username: "); //instantiating new label for username
		userLabel.setBounds(418, 350, 300, 30); //setting bounds
		userLabel.setFont(buttonFont); //setting font for userlabel
		password = new JPasswordField(); //instantiating new password field 
		password.setBounds(500, 395, 300, 20); //setting bounds
		passLabel = new JLabel("Password: "); //instantiating new label for password
		passLabel.setBounds(420, 390, 300, 30); //setting bounds
		passLabel.setFont(buttonFont); //setting font
		login = new JButton("Login"); //instantiating new JButton
		login.setBounds(590, 440, 100, 30); //setting bounds
		makeButton(login); 
		register = new JButton("Register"); //instantiating new JButton 
		register.setBounds(590,520,100,30); //setting bounds
		makeButton(register);
		backgroundLabel.add(userName); //adding username field
		backgroundLabel.add(password); //adding password field
		backgroundLabel.add(userLabel); //adding username label
		backgroundLabel.add(passLabel); //adding password label
		f.setSize(1280,972); //setting frame size
		f.setLayout(null); 
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true); //enabling visibility
		f.setLocationRelativeTo(null); //centering
		f.add(backgroundLabel);
		in.close();
	}
	
	/**
	 * saveUsers function to save data by writing accounts array data into the textfile
	 * @throws IOException
	 */
	
	public void saveUsers() throws IOException {
		out = new BufferedWriter(new FileWriter(fileName));
		out.write(""+numOfUsers);
		out.newLine();
		for(int i = 0; i < numOfUsers; i++) {
			out.write(accounts.get(i).getUsername()+ " ");
		}
		out.newLine();

		for(int i = 0; i < numOfUsers; i++) {
			out.write(accounts.get(i).getPassword() + " ");
		}
		out.newLine();
		out.close();
	}
	
	/**
	 * getUsers method to retrieve number of users registered
	 * @return the number of users in the form of an integer
	 */
	
	public static int getUsers() {
		return numOfUsers;
	}
	
	/**
	 * getUser method to retrieve the currently entered username
	 * @return a string containing the current username
	 */
	
	public static String getUser() {
		return curUser;
	}
	
	/**
	 * getAccounts method to retrieve the 2D array of account details 
	 * @return a 2D String array 
	 */
	
	public static ArrayList<UserAccount> getAccounts(){
		return accounts;
	}
	
	/**
	 * getPass method to retrieve the currently entered password
	 * @return a string containing the current password
	 */
	
	public static String getPass() {
		return curPass;
	}
	
	/**
	 * addUser method to add a new user to the accounts array
	 * @throws IOException
	 * @throws NoSuchAlgorithmException 
	 */
	
	public void addUser() throws IOException, NoSuchAlgorithmException {
		System.out.println(curPass);
		System.out.println(PasswordHashing.hashPassword(curPass));
		accounts.add(new UserAccount(curUser, PasswordHashing.hashPassword(curPass)));
		numOfUsers++;
		saveUsers();
	}

	/**
	 * makeButton method to store code changing appearance of a button
	 * @param b
	 */
	
	public void makeButton(JButton b) {
		b.addActionListener(this);
		b.setFocusable(false);
		b.setFont(buttonFont);
		b.setForeground(Color.black);
		b.setBackground(Color.orange);
		backgroundLabel.add(b);
	}

	/**
	 * accessGranted method to pop up a message dialog stating that access is granted
	 */
	
	public void accessGranted() {
		JOptionPane.showMessageDialog(this, "Access Granted!");
	}

	/**
	 * register method to allow the user to register, meaning a new entry to the accounts array and granting access
	 * @throws FontFormatException
	 * @throws NoSuchAlgorithmException 
	 */
	
	@SuppressWarnings("deprecation")
	public void register() throws FontFormatException, LineUnavailableException, UnsupportedAudioFileException, NoSuchAlgorithmException {
		curUser = userName.getText().trim(); 
		curPass = password.getText().trim(); 
		
		try {
			if(findUser(accounts, curUser, curPass)){
				JOptionPane.showMessageDialog(this, "Error: An account with this username already exists.");
			} else if(curUser.equals("")|| curPass.equals("")) {
				JOptionPane.showMessageDialog(this, "Error: Please enter valid credentials!");
			} else {
				addUser();
				accessGranted();
				register.setEnabled(false);
				new MainMenu();	
				f.dispose();
			}

		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	private boolean findUser(ArrayList<UserAccount> accounts, String curUser, String curPass) throws NoSuchAlgorithmException {
		System.out.println("Current account: " + curUser + " " + PasswordHashing.hashPassword(curPass));
		for(UserAccount u : accounts) {
			System.out.println(u.getUsername() + " " +  u.getPassword());
			if(u.getUsername().equals(curUser) && u.getPassword().equals(PasswordHashing.hashPassword(curPass))) {
				return true;
			}
		}
		return false;
	}

	/**
	 * login method to allow a preexisting user to login to the game
	 * @throws FontFormatException
	 * @throws NoSuchAlgorithmException 
	 * @throws HeadlessException 
	 */
	
	@SuppressWarnings("deprecation")
	public void login() throws FontFormatException, IOException, LineUnavailableException, UnsupportedAudioFileException, HeadlessException, NoSuchAlgorithmException {
		curUser = userName.getText(); 
		curPass = password.getText(); 
		if(findUser(accounts, curUser, curPass)) {
			accessGranted();
			login.setEnabled(false);
			new MainMenu();
			f.dispose();
		} else {
			JOptionPane.showMessageDialog(this, "Error, you have not entered the correct username or password.");
		}
	}
	
	/** 
	 * actionPerformed method to keep track of the users clicks and actions
	 */
	
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == login) {
			try {
				login();
			} catch (FontFormatException e1) {
				e1.printStackTrace();
			} catch (NullPointerException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (LineUnavailableException e1) {
				e1.printStackTrace();
			} catch (UnsupportedAudioFileException e1) {
				e1.printStackTrace();
			} catch (HeadlessException e1) {
				e1.printStackTrace();
			} catch (NoSuchAlgorithmException e1) {
				e1.printStackTrace();
			} 
		} else if (e.getSource() == register) {
			try {
				register();
			} catch (FontFormatException e1) {
				e1.printStackTrace();
			} catch (NullPointerException e1) {
				e1.printStackTrace();
			} catch (LineUnavailableException e1) {
				e1.printStackTrace();
			} catch (UnsupportedAudioFileException e1) {
				e1.printStackTrace();
			} catch (NoSuchAlgorithmException e1) {
				e1.printStackTrace();
			}
		} 

	}
}

