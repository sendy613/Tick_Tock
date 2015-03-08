package tick_tock;

import java.awt.Color;
import java.awt.Graphics;

//coordinates for the clock numbers, face and hands

public class Clock {
	private TimeNow time;

	public Clock() {
		time = new TimeNow();
	}

	public TimeNow getTime() {
		return time;
	}

	public Graphics paint(Graphics g) {
		int hours = time.getHours();
		int minutes = time.getMin();
		int seconds = time.getSeconds();
		int radiusWithMargin = Coordinates.radius + Coordinates.clockMargin;
		int circleWidth = 20;
		Coordinates coord = new Coordinates();

		// draw Circle of clock
		g.setColor(Color.BLACK);
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

		return g;
	}
}
