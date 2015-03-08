package tick_tock;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Alarms {
	private ArrayList<TimeNow> list;
	private Image ringImage;

	public Alarms() {
		this.list = new ArrayList<TimeNow>();
		ringImage = null;
		try {
			ringImage = ImageIO.read(new File("alarmRing.png"));
		} catch (IOException e) {
		}
	}

	public boolean checkIfCurrentAlarms(TimeNow time) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).equals(time)) {
				return true;
			}
		}
		return false;
	}

	public void add(TimeNow time) {
		list.add(time);
	}

	public String toString() {
		StringBuilder info = new StringBuilder();
		for (TimeNow alarm : list) {
			info.append(alarm.toString());
		}
		return info.toString();
	}

	public Graphics paint(Graphics g, TimeNow time) {
		if (checkIfCurrentAlarms(time)) {
			g.drawImage(ringImage, 0, 250, 100, 100, null);
		}
		return g;
	}
}