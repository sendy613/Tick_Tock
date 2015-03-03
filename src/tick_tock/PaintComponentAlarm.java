package tick_tock;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

public class PaintComponentAlarm extends JComponent{

	public PaintComponentAlarm(){
		
	}
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Image image = null;
		try {
			image = ImageIO.read(new File("alarmRing.png"));
		} catch (IOException e) {
		}
		g.drawImage(image, 500, 250, 100, 100, null);
	}
	
}
