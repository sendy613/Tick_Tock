package tick_tock;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
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
	private JPanel picturePanel;
	private AlarmComponent comp2;
	private JPanel buttonPanel;
	public JButton snooze;
	public JButton dismiss;

	public ClockFrame() {
		setSize(700, 525);
		setTitle("CLOCK");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);

		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());
		this.comp = new PaintComponent();
		this.comp2 = new AlarmComponent(comp.getAlarms(), comp.getClock());
		contentPane.add(comp, BorderLayout.CENTER);
		eastPanel = new JPanel();
		eastPanel.setLayout(new BoxLayout(eastPanel, BoxLayout.PAGE_AXIS));
		eastPanel.setBackground(Color.WHITE);
		this.button = new JButton("Add Alarm");
		Color blue = new Color(0, 204, 204);
		button.setBackground(blue);
		Integer[] hours = new Integer[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11,
				12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23 };
		Integer[] minutes = new Integer[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
				11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26,
				27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42,
				43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58,
				59 };
		hoursList = new JComboBox<Integer>(hours);
		minList = new JComboBox<Integer>(minutes);
		hoursList.setBackground(blue);
		minList.setBackground(blue);

		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Integer alarmHour = (Integer) hoursList.getSelectedItem();
				Integer alarmMinute = (Integer) minList.getSelectedItem();
				TimeNow alarm = new TimeNow(alarmHour, alarmMinute, 0);
				comp.getAlarms().add(alarm);
				if (comp.getAlarms().getCounter() == 10) {
					hoursList.setEnabled(false);
					minList.setEnabled(false);
					button.setEnabled(false);
				}
			}
		});
		buttonPanel = new JPanel();
		buttonPanel.setBackground(Color.WHITE);
		buttonPanel.setLayout(new FlowLayout());
		snooze = new JButton("Snooze");
		snooze.setBackground(blue);
		snooze.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				comp.getAlarms().snooze();

			}
		});

		dismiss = new JButton("Dismiss");
		dismiss.setBackground(blue);
		dismiss.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				comp.getAlarms().dismiss();
			}
		});

		inputPanel = new JPanel(new FlowLayout());
		inputPanel.setBackground(Color.WHITE);
		outputPanel = new JPanel();
		outputPanel.setLayout(new BoxLayout(outputPanel, BoxLayout.PAGE_AXIS));
		outputPanel.setBackground(Color.WHITE);
		outputPanel.add(comp.getAlarms().getAlarmList());
		inputPanel.add(hoursList);
		inputPanel.add(minList);
		inputPanel.add(button);
		picturePanel = new JPanel();
		picturePanel
				.setLayout(new BoxLayout(picturePanel, BoxLayout.PAGE_AXIS));
		picturePanel.add(comp2);
		buttonPanel.add(snooze);
		buttonPanel.add(dismiss);
		picturePanel.add(buttonPanel);
		eastPanel.add(inputPanel);
		eastPanel.add(outputPanel);
		eastPanel.add(picturePanel);
		contentPane.add(eastPanel, BorderLayout.EAST);
	}

	public static void main(String args[]) {
		final ClockFrame frame = new ClockFrame();
		frame.setVisible(true);

		Thread thread = new Thread() {
			public void run() {
				Clip clip = null;
				try {
					clip = AudioSystem.getClip();
					AudioInputStream inputStream = AudioSystem
							.getAudioInputStream(new File("Alarm Ringing.wav"));
					clip.open(inputStream);
				} catch (FileNotFoundException e1) {
				} catch (IOException e) {
				} catch (LineUnavailableException e) {
				} catch (UnsupportedAudioFileException e) {
				}
				while (true) {
					if (frame.hoursList.isEnabled()
							&& frame.comp.getAlarms().getCounter() > 10) {
						frame.hoursList.setEnabled(false);
						frame.minList.setEnabled(false);
						frame.button.setEnabled(false);
					} else if (!frame.hoursList.isEnabled()
							&& frame.comp.getAlarms().getCounter() < 10) {
						frame.hoursList.setEnabled(true);
						frame.minList.setEnabled(true);
						frame.button.setEnabled(true);
					}
					frame.comp.getClock().getTime().tick();
					frame.comp.getAlarms().checkIfCurrentAlarms(
							frame.comp.getClock().getTime());
					frame.repaint();
					if (frame.comp.getAlarms().getRing() == true) {
						clip.start();
						frame.snooze.setEnabled(true);
						frame.dismiss.setEnabled(true);
					} else {
						clip.stop();
						clip.setFramePosition(0);
						frame.snooze.setEnabled(false);
						frame.dismiss.setEnabled(false);
					}
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
