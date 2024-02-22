import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Map extends JFrame {
	private BufferedImage map1, map2;
	private JLabel backgroundLabel;
	private int currentFloor = 1;
	private JButton back, changeFloor;
	private ImagePanel map;
	private Font textFont = new Font("Verdana", Font.BOLD, 14);
	public Map() {
		map = new ImagePanel();
		
		add(map);
		setSize(1920, 1080);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void loadMaps() {
        try {
            map1 = ImageIO.read(new File("./res/first_floor.png"));
            map2 = ImageIO.read(new File("./res/second_floor-1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	private void changeFloor() {
        currentFloor = 3 - currentFloor;
        ((ImagePanel) map).setCurrentMap(currentFloor);
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
	
}
