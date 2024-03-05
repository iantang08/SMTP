import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Map extends JFrame implements ActionListener, MouseListener {
	private BufferedImage map1, map2, currentMap;
	private JLabel backgroundLabel;
	private static int currentFloor = 1;
	private JButton back, changeFloor;
	private ImagePanel map;
	private Font textFont = new Font("Verdana", Font.BOLD, 14);
	public Map() {
		Incident.loadData();
		LocationCounter.loadCount();
		loadMaps();
		initUI();
	}
	
	private void initUI() {
        map = new ImagePanel(); 
        changeFloor = new JButton("Change Map");
        changeFloor.addActionListener(this);
        back = new JButton("Back");
        back.addActionListener(this);
        map.add(changeFloor); 
        map.add(back);
        map.addMouseListener(this);
        map.revalidate();
        map.repaint();
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
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == changeFloor) {
			changeFloor();
		} else if(e.getSource() == back) {
			dispose();
			try {
				new MainMenu();
			} catch (FontFormatException | IOException | LineUnavailableException | UnsupportedAudioFileException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	
	public void mouseClicked(MouseEvent e) {
		int x = e.getX(), y = e.getY();
	    if(validPoint(x, y)) {
	    	// Prompt the user to confirm if they want to place a marker
	    	map.showPreviewMarker(x, y);
	    	map.revalidate();
	    	map.repaint();
	    	int response = JOptionPane.showConfirmDialog(this, "Do you want to place a marker at this location?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
	        if (response == JOptionPane.YES_OPTION) {	
	        	map.clearPreviewMarker();
	        	map.addMarker(x, y);
	        	System.out.println("Placing a marker at: " + (x) + " " + (y));
	        	new IncidentInputPanel(Incident.getIncidents(), x, y);
	        } else {
	        	map.clearPreviewMarker();
	        }
	        map.revalidate();
	        map.repaint();
	    } else {
	    	JOptionPane.showMessageDialog(this, "That is an invalid location!");
	    }
    }

    public boolean wb(int n, int lb, int ub) {
    	return (n >= lb && n <= ub);
    }
    public boolean validPoint(int x, int y) {
    	if(currentFloor == 1) {
    		if(wb(x, 330, 583) && wb(y, 315, 342))return true;
    		if(wb(x, 320, 360) && wb(y, 315, 405))return true;
    		if(wb(x, 590, 640) && wb(y, 240, 790))return true;
    		if(wb(x, 650, 725) && wb(y, 212, 243))return true;
    		if(wb(x, 712, 945) && wb(y, 251, 264))return true;
    		if(wb(x, 930, 945) && wb(y, 270, 405))return true;
    		if(wb(x, 650, 905) && wb(y, 410, 445))return true;
    		if(wb(x, 650, 1085) && wb(y, 410, 425))return true;
    		if(wb(x, 890, 905) && wb(y, 415, 565))return true;
    		if(wb(x, 510, 1290) && wb(y, 535, 570))return true;
    		if(wb(x, 810, 825) && wb(y,535,790))return true;
    		if(wb(x, 285, 480) && wb(y, 435, 685))return true;
    		if(wb(x, 285, 560) && wb(y, 435, 485))return true;
    		if(wb(x, 485, 520) && wb(y, 595, 685))return true;
    		if(wb(x, 470, 1290) && wb(y, 755, 790))return true;
    		if(wb(x, 1260, 1292) && wb(y, 455, 845))return true;
    		if(wb(x, 670, 725) && wb(y, 760, 930))return true;
    		if(wb(x, 425, 463) && wb(y, 778, 890))return true;
    		if(wb(x, 425, 441) && wb(y, 778, 930))return true;
    		if(wb(x, 1360, 1650) && wb(y, 453, 855))return true;
    	} else if(currentFloor == 2) {
    		if(wb(x, 280, 1010) && wb(y, 345, 380))return true;
    		if(wb(x, 955, 1015) && wb(y, 280, 990))return true;
    		if(wb(x, 1360, 1415) && wb(y, 280, 990))return true;
    	}
    	return false;
    }
	public void mousePressed(MouseEvent e) {	
	}
	public void mouseReleased(MouseEvent e) {		
	}
	public void mouseEntered(MouseEvent e) {		
	}
	public void mouseExited(MouseEvent e) {		
	}

	public static int getCurrentFloor() {
		return currentFloor;
	}
	
}
