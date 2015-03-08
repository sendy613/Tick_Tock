package tick_tock;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

public class PaintComponentAlarm extends JComponent {

	private static final long serialVersionUID = 1L;
	private Image image;

	public PaintComponentAlarm() {
		image = null;
		try {
			image = ImageIO.read(new File("alarmRing.png"));
		} catch (IOException e) {
		}
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 250, 100, 100, null);
	}

}
