package szoftlab3;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;

/** A Canvas supporting 2 layers: bottom for permanent, top for temporary draws. */
public class MyCanvas extends JComponent {

	/** Making eclipse happy */
	private static final long serialVersionUID = 1L;
	
	/** Creates 3 images */
	public MyCanvas() {
		super();
		bottom = new BufferedImage(d.width,d.height,BufferedImage.TYPE_INT_RGB);
		bottom.getGraphics().setColor(Color.white);
		bottom.getGraphics().fillRect(0,0,d.width, d.height);
		image = new BufferedImage(d.width,d.height,BufferedImage.TYPE_INT_RGB);
		top = new BufferedImage(d.width,d.height,BufferedImage.TYPE_INT_ARGB);
	}
	/** The image that will be drawn to the components Graphics */
	private BufferedImage image;
	/** The bottom layer of the image */
	private BufferedImage bottom;
	/** The temporary, transparent top layer of the image */ 
	private BufferedImage top;
	/** The default size */
	private Dimension d = new Dimension(500,500);
	
	public Graphics getBottom() {
		return bottom.getGraphics();
	}
	public Graphics getTop() {
		return top.getGraphics();
	}
	
	public Dimension getMinimumSize() { 
		return d;
	}
	
	public Dimension getMaximumSize() { 
		return d;
	}
	
	public Dimension getPreferredSize() { 
		return d;
	}
	/** Paints the component. Puts bottom and top on to image, then draws image. */
	public void paint(Graphics g) {
		Graphics g0 = image.getGraphics();
		g0.drawImage(bottom,0,0,null);
		g0.drawImage(top, 0,0,null);
		g.drawImage(image,0,0,null);
	}
	
}
