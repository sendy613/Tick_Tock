package tick_tock;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JComponent;

public class PaintComponent extends JComponent {

	private static final long serialVersionUID = 1L;
	private Clock clock;
	private Alarms alarms;
	private Image TwelveSixAM;
	private Image SixSix;
	private Image SixEightPM;
	private Image EightTwelvePM;

	public PaintComponent() {
		clock = new Clock();
		alarms = new Alarms();
		try {
			TwelveSixAM = ImageIO.read(new File("12-6am.png"));
			SixSix = ImageIO.read(new File("6-6.png"));
			SixEightPM = ImageIO.read(new File("6-8pm.png"));
			EightTwelvePM = ImageIO.read(new File("8-12pm.png"));

		} catch (IOException e) {
		}
	}

	public Clock getClock() {
		return clock;
	}

	public Alarms getAlarms() {
		return alarms;
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		int hours = clock.getTime().getHours();
		Image image = null;
		if (hours >= 0 && hours <= 6) {
			image = TwelveSixAM;
		} else if (hours >= 7 && hours <= 18) {
			image = SixSix;
		} else if (hours == 19 || hours == 20) {
			image = SixEightPM;
		} else if (hours == 21 || hours == 22 || hours == 23) {
			image = EightTwelvePM;
		}
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);

		g = clock.paint(g);

	}
}
