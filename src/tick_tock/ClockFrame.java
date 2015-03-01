package tick_tock;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;


public class ClockFrame extends JFrame {
	private PaintComponent comp;
	public ClockFrame(){
	setSize(700, 500);
	setTitle("CLOCK");
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setResizable(false);

	Container contentPane = getContentPane();
	contentPane.setLayout(new BorderLayout());
	this.comp = new PaintComponent();
	contentPane.add(comp, BorderLayout.CENTER);
}



public static void main(String args[]) {
	final ClockFrame frame = new ClockFrame();
	frame.setVisible(true);

	// animation thread- bec you are constantly calling repaint
	Thread thread = new Thread() {
		public void run() {
			while (true) {
				frame.comp.getClock().getTime().tick();
				frame.repaint();
				try {
					Thread.sleep(1000);

				} catch (InterruptedException e) {

					e.printStackTrace();
				}
			}

		}
	};
	thread.start();
}
}

