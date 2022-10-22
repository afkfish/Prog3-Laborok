package szoftlab3;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * This class contains the entry point to the application. It initializes the
 * application in its show method.
 */
public class Main implements Runnable {
	/** Entry point of the setup of the GUI */
	public void run() {
		JFrame frame = new JFrame();

		JButton line = new JButton("Line");
		JButton color = new JButton("Color");
		JButton rect = new JButton("Rectangle");
		JButton circle = new JButton("Circle");
		JButton curve = new JButton("Curve");

		JPanel west = new JPanel();
		BoxLayout box = new BoxLayout(west, BoxLayout.Y_AXIS);
		west.setLayout(box);

		west.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		west.add(line);
		west.add(rect);
		west.add(circle);
		west.add(curve);
		west.add(Box.createRigidArea(new Dimension(0, 15)));
		west.add(color);

		frame.add(west, BorderLayout.WEST);

		MyCanvas mc = new MyCanvas();
		JScrollPane jsp = new JScrollPane(mc);
		frame.add(jsp);

		MouseListenerDelegator mld = new MouseListenerDelegator(mc);
		mld.setDelegate(new RectDraw());

		ButtonListener bl = new ButtonListener(mld);
		rect.addActionListener(bl);
		circle.addActionListener(bl);
		line.addActionListener(bl);
		curve.addActionListener(bl);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		// Schedule a job for the event-dispatching thread:
		// creating and showing this application's GUI.
		Main m = new Main();
		javax.swing.SwingUtilities.invokeLater(m);
	}

}
