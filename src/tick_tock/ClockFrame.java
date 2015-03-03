package tick_tock;

import java.awt.BorderLayout;
import java.awt.Container;
import java.util.Scanner;

import javax.swing.JFrame;

public class ClockFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	private PaintComponent comp;

	// take out after
	private TimeNow testAlarm;

	public ClockFrame() {
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

		// take out after
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Enter an alarm time in army time. (hh mm ss)");
		String input = keyboard.nextLine();
		String[] array = input.split(" ");
		int hour = Integer.parseInt(array[0]);
		int min = Integer.parseInt(array[1]);
		int sec = Integer.parseInt(array[2]);
		frame.testAlarm = new TimeNow(hour, min, sec);

		Thread thread = new Thread() {
			public void run() {
				while (true) {

					// take out after
					if (frame.comp.getClock().getTime()
							.equalsHoursMin(frame.testAlarm)) {
						System.out.println("alarm");
					}

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
		keyboard.close();
	}
}
