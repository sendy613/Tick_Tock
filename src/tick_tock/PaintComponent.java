package tick_tock;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

public class PaintComponent extends JComponent {

	private static final long serialVersionUID = 1L;
	private Clock clock;

	public PaintComponent() {
		this.clock = new Clock();
	}

	public Clock getClock() {
		return clock;
	}

	protected void paintComponent(Graphics g) {
		// I made the frame 500x700 but based the clock as if it was in a frame
		// of 500x500.
		// the remaining 200 should be used for setting the alarms
		super.paintComponent(g);

		Coordinates coord = new Coordinates();
		int hours = clock.getTime().getHours();
		int minutes = clock.getTime().getMin();
		int seconds = clock.getTime().getSeconds();

		Image image = null;
		try {
			if (hours >= 0 && hours <= 6) {
				image = ImageIO.read(new File("12-6am.png"));
			} else if (hours >= 7 && hours <= 18) {
				image = ImageIO.read(new File("6-6.png"));
			} else if (hours == 19 || hours == 20) {
				image = ImageIO.read(new File("6-8pm.png"));
			} else if (hours == 21 || hours == 22 || hours == 23) {
				image = ImageIO.read(new File("8-12pm.png"));
			}
		} catch (IOException e) {
		}
<<<<<<< HEAD

		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
=======
		g.drawImage(image, 0, 0, 500, 500, null);
>>>>>>> origin/master
		g.setColor(Color.BLACK);
		g.drawString(clock.getTime().toString(), 10, 10);
		g.fillOval(25, 10, 450, 450);
		g.setColor(Color.WHITE);
		// 12
		g.fillOval(240, 35, 25, 25);
		// 6
		g.fillOval(240, 410, 25, 25);
		// 9
		g.fillOval(50, 215, 25, 25);
		// 3
		g.fillOval(425, 215, 25, 25);

		g.setColor(Color.WHITE);
		// button for handles
		g.fillOval(245, 225, 10, 10);

		// handles
		g.drawLine(250, 230, coord.getX(seconds) * 10 + 25,
				coord.getY(seconds) * 10 + 10); // seconds
		g.drawLine(250, 230, coord.getX(minutes) * 10 + 25,
				coord.getY(minutes) * 10 + 10); // minutes
		if (hours > 12) {
			hours -= 12;
		}
		int hourPointer = hours * 5 + (minutes / 12);
		g.drawLine(250, 230, coord.getX(hourPointer) * 10 + 25,
				coord.getY(hourPointer) * 10 + 10); // hours
		
	}
}
