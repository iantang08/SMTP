import java.awt.Color;
import java.awt.Graphics;
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
import javax.swing.JPanel;

/**
 * a panel that displays an image with the ability to interact with mouse events and place markers.
 */
public class ImagePanel extends JPanel implements MouseListener {
    private static BufferedImage map1, map2, currentMap;
    private Optional<Point> previewMarkerPoint = Optional.empty();
    private static List<Point> markers1 = new ArrayList<>(), markers2 = new ArrayList<>();
    private List<Point> currentMarkers = markers1;

    /**
     * initializes the image panel, loading two map images and setting the first one as the current map.
     */
    public ImagePanel() {
        super();
        try {
            map1 = ImageIO.read(new File("./res/first_floor.png"));
            map2 = ImageIO.read(new File("./res/second_floor.png"));
            currentMap = map1; // set default map to first floor
        } catch (IOException e) {
            e.printStackTrace();
        }   
    }
    
    /**
     * custom painting method for the panel that draws the current map and any markers including a preview marker.
     * @param g graphics context for drawing
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // draw the current map image adjusted to the panel size
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
        // draw preview marker if present
        previewMarkerPoint.ifPresent(point -> {
            g.setColor(Color.MAGENTA);
            g.fillOval(point.x - 5, point.y - 5, 10, 10);
        });
        // draw all markers for the current map
        g.setColor(Color.RED);
        for (Point marker : currentMarkers) {
            g.fillOval(marker.x - 5, marker.y - 5, 10, 10);
        }
    }

    /**
     * shows a preview marker at specified coordinates.
     * @param x x-coordinate for preview marker
     * @param y y-coordinate for preview marker
     */
    public void showPreviewMarker(int x, int y) {
        previewMarkerPoint = Optional.of(new Point(x, y));
        repaint();
    }

    /**
     * clears the preview marker.
     */
    public void clearPreviewMarker() {
        previewMarkerPoint = Optional.empty();
        repaint();
    }

    /**
     * sets the current map and corresponding markers list based on the given floor number.
     * @param n floor number to set the current map
     */
    public void setCurrentMap(int n) {
        // switch current map and markers list based on floor number
        if (n == 1) {
            currentMap = map1;
            currentMarkers = markers1;
        } else {
            currentMap = map2;
            currentMarkers = markers2;
        }
        repaint();
    }

    /**
     * adds a marker at the specified coordinates for the current map.
     * @param floor - current floor of school
     * @param x x-coordinate for the new marker
     * @param y y-coordinate for the new marker
     */
    public static void addMarker(int floor, int x, int y){
		Point p = new Point(x,y);
		if(floor==1)markers1.add(p);
		else
			markers2.add(p);
	}

    /**
     * returns the current map image.
     * @return current map as BufferedImage
     */
    public BufferedImage getCurrentMap() {
    	return currentMap;
    }

    // mouse event methods with no implementation, required by MouseListener interface
    @Override public void mouseClicked(MouseEvent e) {}
    @Override public void mousePressed(MouseEvent e) {}
    @Override public void mouseReleased(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}
}
