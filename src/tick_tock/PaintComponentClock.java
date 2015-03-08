package tick_tock;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

public class PaintComponentClock extends JComponent {

	private static final long serialVersionUID = 1L;
	private Clock clock;
	private Image TwelveSixAM;
	private Image SixSix;
	private Image SixEightPM;
	private Image EightTwelvePM;

	public PaintComponentClock() {
		this.clock = new Clock();
		try {
			this.TwelveSixAM = ImageIO.read(new File("12-6am.png"));
			this.SixSix = ImageIO.read(new File("6-6.png"));
			this.SixEightPM = ImageIO.read(new File("6-8pm.png"));
			this.EightTwelvePM = ImageIO.read(new File("8-12pm.png"));

		} catch (IOException e) {
		}
	}

	public Clock getClock() {
		return clock;
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		Coordinates coord = new Coordinates();
		int hours = clock.getTime().getHours();
		int minutes = clock.getTime().getMin();
		int seconds = clock.getTime().getSeconds();

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

		int radiusWithMargin = Coordinates.radius + Coordinates.clockMargin;
		int circleWidth = 20;

		// draw Circle of clock
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		g.setColor(Color.BLACK);
		g.drawString(clock.getTime().toString(), 10, 10);
		g.fillOval(Coordinates.clockMargin, Coordinates.clockMargin,
				Coordinates.radius * 2, Coordinates.radius * 2);

		g.setColor(Color.WHITE);
		// 12
		g.fillOval(radiusWithMargin - circleWidth / 2, Coordinates.clockMargin
				+ 25 - circleWidth / 2, circleWidth, circleWidth);
		// 6
		g.fillOval(radiusWithMargin - circleWidth / 2, Coordinates.radius * 2
				+ Coordinates.clockMargin - 25 - circleWidth / 2, circleWidth,
				circleWidth);
		// 9
		g.fillOval(Coordinates.clockMargin + 25 - circleWidth / 2,
				radiusWithMargin - circleWidth / 2, circleWidth, circleWidth);
		// 3
		g.fillOval(Coordinates.radius * 2 + Coordinates.clockMargin - 25
				- circleWidth / 2, radiusWithMargin - circleWidth / 2,
				circleWidth, circleWidth);

		// button for handles
		circleWidth = 10;
		g.setColor(Color.WHITE);
		g.fillOval(radiusWithMargin - circleWidth / 2, radiusWithMargin
				- circleWidth / 2, circleWidth, circleWidth);

		// handles
		g.drawLine(radiusWithMargin, radiusWithMargin, coord.getX(seconds),
				coord.getY(seconds)); // seconds
		g.drawLine(radiusWithMargin, radiusWithMargin, coord.getX(minutes),
				coord.getY(minutes)); // minutes
		if (hours > 12) {
			hours -= 12;
		}
		int hourPointer = hours * 5 + (minutes / 12);
		g.drawLine(radiusWithMargin, radiusWithMargin,
				coord.getHoursX(hourPointer), coord.getHoursY(hourPointer)); // hours

	}
}
