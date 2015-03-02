package tick_tock;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

public class PaintComponent extends JComponent {
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
		// the remaining 200 shoukd be used for setting the alarms
		super.paintComponent(g);
		Image image = null;
		if (clock.getTime().getHours() == 0 || clock.getTime().getHours() == 1
				|| clock.getTime().getHours() == 2
				|| clock.getTime().getHours() == 3
				|| clock.getTime().getHours() == 4
				|| clock.getTime().getHours() == 5
				|| clock.getTime().getHours() == 6) {
			try {
				image = ImageIO.read(new File("12-6am.png"));
			} catch (IOException e) {
			}

		} else if (clock.getTime().getHours() == 7
				|| clock.getTime().getHours() == 8
				|| clock.getTime().getHours() == 9
				|| clock.getTime().getHours() == 10
				|| clock.getTime().getHours() == 11
				|| clock.getTime().getHours() == 12
				|| clock.getTime().getHours() == 13
				|| clock.getTime().getHours() == 14
				|| clock.getTime().getHours() == 15
				|| clock.getTime().getHours() == 16
				|| clock.getTime().getHours() == 17
				|| clock.getTime().getHours() == 18) {
			try {
				image = ImageIO.read(new File("6-6.png"));
			} catch (IOException e) {
			}
		} else if (clock.getTime().getHours() == 19
				|| clock.getTime().getHours() == 20) {
			try {
				image = ImageIO.read(new File("6-8pm.png"));
			} catch (IOException e) {
			}
		} else if (clock.getTime().getHours() == 21
				|| clock.getTime().getHours() == 22
				|| clock.getTime().getHours() == 23) {
			try {
				image = ImageIO.read(new File("8-12pm.png"));
			} catch (IOException e) {
			}
		}
		g.drawImage(image, 0, 0, 700, 500, null);
		g.setColor(Color.BLACK);
		g.drawString(clock.getTime().toString(), 10, 10);
		g.fillOval(25, 10, 450, 450);
		g.setColor(Color.YELLOW);
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
		// min handle

		// hour handle

		// second handle

	}
}
