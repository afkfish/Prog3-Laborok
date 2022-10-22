package szoftlab3;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/** Helper class for easy change of Draws in MyCanvas. 
 * All Listener methods are delegated to delegate. */
public class MouseListenerDelegator implements MouseListener,
		MouseMotionListener {

	Draw delegate;
	MyCanvas canvas;
	/** Constructor for setting canvas and adding itself to canvas as Mouse*Listener. */
	public MouseListenerDelegator(MyCanvas mc) {
		canvas = mc;
		canvas.addMouseListener(this);
		canvas.addMouseMotionListener(this);
	}

	public Draw getDelegate() {
		return delegate;
	}

	public void setDelegate(Draw delegate) {
		this.delegate = delegate;
		delegate.setCanvas(canvas);
	}

	public void mouseMoved(MouseEvent arg0) {
		delegate.mouseMoved(arg0);
	}

	public void mouseClicked(MouseEvent arg0) {
		delegate.mouseClicked(arg0);
	}

	public void mouseEntered(MouseEvent arg0) {
		delegate.mouseEntered(arg0);
	}

	public void mouseExited(MouseEvent arg0) {
		delegate.mouseExited(arg0);
	}

	public void mousePressed(MouseEvent arg0) {
		delegate.mousePressed(arg0);
	}

	public void mouseReleased(MouseEvent arg0) {
		delegate.mouseReleased(arg0);
	}

	public void mouseDragged(MouseEvent arg0) {
		delegate.mouseDragged(arg0);
	}
	
	

}
