import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ImagePanel extends JPanel implements MouseListener {
    private static BufferedImage map1, map2, currentMap;
    private Optional<Point> previewMarkerPoint = Optional.empty();
    private static List<Point> markers1 = new ArrayList<>(), markers2 = new ArrayList<>();
	private List<Point> currentMarkers = markers1;

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
            double aspectRatio = (double) currentMap.getWidth() / currentMap.getHeight();
            int panelWidth = this.getWidth();
            int panelHeight = this.getHeight();
            int imageWidth = panelWidth;
            int imageHeight = (int) (panelWidth / aspectRatio);
            
            if (imageHeight > panelHeight) {
                imageHeight = panelHeight;
                imageWidth = (int) (imageHeight * aspectRatio);
            }
            
            int x = (panelWidth - imageWidth) / 2;
            int y = (panelHeight - imageHeight) / 2;
            
            g.drawImage(currentMap, x, y, imageWidth, imageHeight, this);
        }
        previewMarkerPoint.ifPresent(point -> {
            g.setColor(Color.MAGENTA);
            g.fillOval(point.x - 5, point.y - 5, 10, 10);
        });
        
        g.setColor(Color.RED);
        for (Point marker : currentMarkers) {
            g.fillOval(marker.x - 5, marker.y - 5, 10, 10); // Draw each marker
        }
    }
    public void showPreviewMarker(int x, int y) {
        // Store the preview marker location
        previewMarkerPoint = Optional.of(new Point(x, y));
        repaint(); // Trigger a repaint to show the preview marker
    }
    public void clearPreviewMarker() {
        // Clear the preview marker
        previewMarkerPoint = Optional.empty();
        repaint(); // Trigger a repaint to remove the preview marker
    }
    public void setCurrentMap(int n) {
        if (n == 1) {
            currentMap = map1;
            currentMarkers = markers1;
        } else {
            currentMap = map2;
            currentMarkers = markers2;
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
	
    public void addMarker(int x, int y) {
        if(Map.getCurrentFloor() == 1)markers1.add(new Point(x, y)); // Add the marker to the list
        else
        	markers2.add(new Point(x,y));
        repaint(); // Trigger a repaint to show the new marker
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
	
	public static void addMarker(int floor, int x, int y){
		Point p = new Point(x,y);
		if(floor==1)markers1.add(p);
		else
			markers2.add(p);
	}
}
