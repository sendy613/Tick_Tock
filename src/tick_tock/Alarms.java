package tick_tock;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

import javax.imageio.ImageIO;

public class Alarms {
	private ArrayList<TimeNow> alarmTimes;
	private Vector<String> alarmStrings;
	private Image ringImage;
	private boolean ring;

	public Alarms() {
		alarmTimes = new ArrayList<TimeNow>();
		alarmStrings = new Vector<String>();
		ring = false;
		ringImage = null;
		try {
			ringImage = ImageIO.read(new File("alarmRing.png"));
		} catch (IOException e) {
		}
	}

	public void add(TimeNow time) {
		alarmTimes.add(time);
		alarmStrings.add(time.toString());
	}

	public boolean getRing() {
		return ring;
	}

	public Vector<String> getAlarmStrings() {
		return alarmStrings;
	}

	public void checkIfCurrentAlarms(TimeNow time) {
		for (TimeNow temp : alarmTimes) {
			if (temp.equals(time)) {
				ring = true;
				if (time.getSeconds() == 59) {
					alarmTimes.remove(temp);
				}
				return;
			}
		}
		ring = false;
	}

	public String toString() {
		StringBuilder info = new StringBuilder();
		for (TimeNow alarm : alarmTimes) {
			info.append(alarm.toString());
			info.append(" ");
		}
		return info.toString();
	}

	public Graphics paint(Graphics g, TimeNow time) {
		checkIfCurrentAlarms(time);
		if (ring) {
			int size = 200;
			g.drawImage(ringImage, Coordinates.radius + Coordinates.clockMargin
					- size / 2, Coordinates.radius + Coordinates.clockMargin
					- size / 2, size, size, null);
		}
		return g;
	}
}