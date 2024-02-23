import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ImagePanel extends JPanel implements MouseListener {
    private static BufferedImage map1, map2, currentMap;
    
    public ImagePanel() {
        super();
        try {
            map1 = ImageIO.read(new File("./res/first_floor.png"));
            map2 = ImageIO.read(new File("./res/second_floor.png"));
            currentMap = map1; // Default map
        } catch (IOException e) {
            e.printStackTrace();
        }   
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (currentMap != null) {
            g.drawImage(currentMap, 0, 0, this.getWidth(), this.getHeight(), this);
        }
    }
    
    public void setCurrentMap(int n) {
        if (n == 1) {
            currentMap = map1;
        } else {
            currentMap = map2;
        }
        repaint();//repaint after changing the map
    }
    public void placeMarker(int x, int y) {
        // Placeholder for placing a marker at the specified location
        // This could involve updating the map image to include a marker icon at the specified coordinates
        System.out.println("Placing marker at: " + x + ", " + y);
        Graphics2D g = currentMap.createGraphics();
        g.setColor(Color.RED);
        g.fillRect(x, y, 10, 10);
        g.dispose();
        repaint();
    }
	
    public BufferedImage getCurrentMap() {
    	return currentMap;
    }

	@Override
	public void mouseClicked(MouseEvent e) {
	    
	}


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
