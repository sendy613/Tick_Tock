package tick_tock;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

public class ClockFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	private PaintComponent comp;
	private JButton button;
	private JComboBox<Integer> hoursList;
	private JComboBox<Integer> minList;
	private JPanel eastPanel;
	private JPanel inputPanel;
	private JPanel outputPanel;
	private JList<String> alarmList;
	private DefaultListModel<String> alarms;

	public ClockFrame() {
		setSize(700, 525);
		setTitle("CLOCK");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);

		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());
		this.comp = new PaintComponent();
		contentPane.add(comp, BorderLayout.CENTER);
		eastPanel = new JPanel();
		eastPanel.setLayout(new BoxLayout(eastPanel, BoxLayout.PAGE_AXIS));
		this.button = new JButton("Enter Alarm");
		Integer[] hours = new Integer[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21,
				22, 23 };
		Integer[] minutes = new Integer[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20,
				21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46,
				47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59 };
		hoursList = new JComboBox<Integer>(hours);
		minList = new JComboBox<Integer>(minutes);

		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Integer alarmHour = (Integer) hoursList.getSelectedItem();
				Integer alarmMinute = (Integer) minList.getSelectedItem();
				TimeNow alarm = new TimeNow(alarmHour, alarmMinute, 0);
				comp.getAlarms().add(alarm);
				// eastPanel.add(new JLabel(alarm.toString()));
			}
		});

		alarms = new DefaultListModel<String>();
		alarmList = new JList<String>(alarms);
		inputPanel = new JPanel(new FlowLayout());
		outputPanel = new JPanel();
		outputPanel.setLayout(new BoxLayout(outputPanel, BoxLayout.PAGE_AXIS));
		outputPanel.add(alarmList);
		inputPanel.add(hoursList);
		inputPanel.add(minList);
		inputPanel.add(button);
		eastPanel.add(inputPanel);
		eastPanel.add(outputPanel);
		contentPane.add(eastPanel, BorderLayout.EAST);
	}

	public static void main(String args[]) {
		final ClockFrame frame = new ClockFrame();
		frame.setVisible(true);

		Thread thread = new Thread() {
			public void run() {
				while (true) {
					frame.comp.getClock().getTime().tick();
					if (frame.comp.getAlarms().toString() != null) {
						frame.alarms.clear();
						frame.alarms.addElement(frame.comp.getAlarms().toString());
					}
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
