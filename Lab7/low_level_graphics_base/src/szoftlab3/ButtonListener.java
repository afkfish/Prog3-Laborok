package szoftlab3;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/** The class responsible for the change of different shapes when a button is clicked. */
public class ButtonListener implements ActionListener {

	/** The delegator that holds the actual Draw object */
	private MouseListenerDelegator mld;
	
	public ButtonListener(MouseListenerDelegator m) {
		mld = m;
	}
	
	/** The component that was clicked last time */
	private Component last;
		
	/** Sets the Draw subclass according to the action command. The button last
	 * clicked is enabled, current button disabled. 
	 */
	public void actionPerformed(ActionEvent arg0) {
		String cmd = arg0.getActionCommand();
		
		if ("Rectangle".equals(cmd)) {
			mld.setDelegate(new RectDraw());
		} else if ("Circle".equals(cmd)){
			//...
		} //...
		
		if (last != null) last.setEnabled(true);
		last = (Component)arg0.getSource();
		last.setEnabled(false);
		
	}

}
