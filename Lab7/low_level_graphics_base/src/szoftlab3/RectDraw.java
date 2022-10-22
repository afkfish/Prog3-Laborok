package szoftlab3;

import java.awt.Graphics;
/** Class for drawing rectangles */
public class RectDraw extends Draw {

	/** Draws a rectangle using startx, starty, endx, endy. Takes into account that top
	 * left corner and size is to be specified.
	 */
	public void makeDraw(Graphics g) {
		g.drawRect(min(startx, endx), min(starty, endy), dif(startx, endx),
				dif(starty, endy));
	}
	
	
	
}
