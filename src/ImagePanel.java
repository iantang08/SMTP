import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ImagePanel extends JPanel{
	private static BufferedImage map1, map2, currentMap;
	
	public ImagePanel() {
		super();
		try {
			map1 = ImageIO.read(new File("./res/first_floor.png"));
			map2 = ImageIO.read(new File("./res/second_floor-1.png"));
			currentMap = map1;
		} catch (IOException e) {
			e.printStackTrace();
		}	
		
	}
	
	public void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    g.drawImage(currentMap, 0, 0, this);
	    repaint();
	}
	
	public void setCurrentMap(int n) {
		if(n ==1)currentMap = map1;
		else
			currentMap = map2;
		repaint();
	}
	
}
