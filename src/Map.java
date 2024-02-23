import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Map extends JFrame implements ActionListener, MouseListener {
	private BufferedImage map1, map2, currentMap;
	private JLabel backgroundLabel;
	private int currentFloor = 1;
	private JButton back, changeFloor;
	private ImagePanel map;
	private Font textFont = new Font("Verdana", Font.BOLD, 14);
	public Map() {
		loadMaps();
		initUI();
	}
	
	private void initUI() {
        map = new ImagePanel(); // Assuming the first floor is the default
        changeFloor = new JButton("Change Map");
        changeFloor.setBounds(1600, 300, 300, 50); // Adjust size as needed
        changeFloor.addActionListener(this);

        // Adding button directly to the map panel for simplicity
        map.setLayout(new BorderLayout());
        map.add(changeFloor, BorderLayout.SOUTH); // Example placement

        add(map);
        setSize(1920, 1080);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
	public void makeButton(JButton b) {
		b.addActionListener(this);
		b.setFocusable(false);	
		b.setForeground(Color.black);
		b.setBackground(Color.orange);
		backgroundLabel.add(b);
	}
	
	private void loadMaps() {
        try {
            map1 = ImageIO.read(new File("./res/first_floor.png"));
            map2 = ImageIO.read(new File("./res/second_floor.png"));
            currentMap = map1;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	private void changeFloor() {
        currentFloor = 3 - currentFloor;
        map.setCurrentMap(currentFloor);
        currentMap = map.getCurrentMap();
    }

	private void saveFloor() {
		if(currentFloor ==1)
			backgroundLabel = new JLabel(new ImageIcon(map1));
		else
			backgroundLabel = new JLabel(new ImageIcon(map2));
	}
	
	public static void main(String[] args) {
		Map m = new Map();
		
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == changeFloor) {
			changeFloor();
		}
	}
	
	
	public void mouseClicked(MouseEvent e) {
        // Call displayChoices and pass the mouse click coordinates
        
    }

    public void displayChoices(int x, int y) {
        // Prompt the user to confirm if they want to place a marker
        int response = JOptionPane.showConfirmDialog(this, "Do you want to place a marker at this location?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        
        if (response == JOptionPane.YES_OPTION) {
            map.placeMarker(x, y);
        }
    }

	public void mousePressed(MouseEvent e) {		
		displayChoices(e.getX(), e.getY());
	}
	public void mouseReleased(MouseEvent e) {		
	}
	public void mouseEntered(MouseEvent e) {		
	}
	public void mouseExited(MouseEvent e) {		
	}
	
	
}
