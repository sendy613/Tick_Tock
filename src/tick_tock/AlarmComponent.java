package tick_tock;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

public class AlarmComponent extends JComponent{
	private Image sleepingImage;
	private Image ringImage;
	private Alarms alarm;
	private Clock clock;
	public AlarmComponent(){
		try {
			ringImage = ImageIO.read(new File("alarmRing.png"));
			sleepingImage = ImageIO.read(new File("sleepingClock.png"));
		} catch (IOException e) {
		}
		alarm = new Alarms();
		clock = new Clock();
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		alarm.checkIfCurrentAlarms(clock.getTime());
		if (alarm.getRing()) {
			//int size = 200;
			g.drawImage(ringImage, 0, 0, getWidth(), getHeight(), null);
		}
		else{
			g.drawImage(sleepingImage, 0, 0, getWidth(), getHeight(), null);
		}
		
	}
}
