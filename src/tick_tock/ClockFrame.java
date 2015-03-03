package tick_tock;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ClockFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	private PaintComponent comp;
	// private PaintComponentAlarm alarmComp;
	private JButton button;
	private JTextField textBox;

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
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		// this.alarmComp = new PaintComponentAlarm();
		// panel.add(alarmComp);
		this.button = new JButton("Enter");
		this.textBox = new JTextField();
		textBox.setColumns(10);
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String input = textBox.getText();
				// once we can add an Alarms to the PaintComponent we can add
				// this
				// input as an alarm
			}

		});
		panel.add(textBox);
		panel.add(button);

		contentPane.add(panel, BorderLayout.EAST);
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
					if (frame.comp.getClock().getTime().equalsHoursMin(frame.testAlarm)) {
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
