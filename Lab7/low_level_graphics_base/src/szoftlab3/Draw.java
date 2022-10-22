package szoftlab3;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/** Superclass for those drawing on a MyCanvas */
abstract public class Draw implements MouseListener, MouseMotionListener {

	/** The MyCanvas it draws on */
	protected MyCanvas canvas;
	/** Coordinates of mouse pointer when mouse was pressed */
	protected int startx, starty;
	/** Last coordinates of mouse pointer. */
	protected int endx, endy;
	
	void setCanvas(MyCanvas mc) {
		canvas = mc;
	}

	/** Returns smaller int */
	protected int min(int a, int b) {
		return (a < b) ? a : b;
	}

	/** Returns the absolute value of the difference. */
	protected int dif(int a, int b) {
		return (a < b) ? b - a : a - b;
	}

	
	public void mouseMoved(MouseEvent arg0) {
	}

	
	public void mouseClicked(MouseEvent arg0) {
	}

	
	public void mouseEntered(MouseEvent arg0) {
	}

	
	public void mouseExited(MouseEvent arg0) {
	}

	/** Sets the startx and starty coordinates to those of the event. */
	
	public void mousePressed(MouseEvent arg0) {
		startx = endx = arg0.getX();
		starty = endy = arg0.getY();
	}

	/** Draws on the bottom layer of the MyCanvas object. */
	
	public void mouseReleased(MouseEvent arg0) {
		realDraw(arg0, canvas.getBottom());

	}

	/** Draws on the top layer of the MyCanvas object. */
	
	public void mouseDragged(MouseEvent arg0) {
		realDraw(arg0, canvas.getTop());
	}

	/**
	 * The actual drawing algorithm. Draws a transparent image for erasing
	 * previous draw, then draws a new image to g2. Also sets endx and endy.
	 */
	protected void realDraw(MouseEvent arg0, Graphics g2) {
		// clearing previous image
		Graphics2D g = (Graphics2D) canvas.getTop();
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.CLEAR));
		makeDraw(g);
		// reset composite
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
		
		// setting new final point
		endx = arg0.getX();
		endy = arg0.getY();
		// drawing on the bottom layer
		g2.setColor(Color.BLACK);
		makeDraw(g2);
		canvas.update(canvas.getGraphics());
	}

	/** Abstract method for subclasses. It implements the actual drawing. */
	abstract protected void makeDraw(Graphics g);

}
