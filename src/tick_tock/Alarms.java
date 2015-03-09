package tick_tock;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;
import javax.imageio.ImageIO;
import javax.swing.JList;

public class Alarms {
	private ArrayList<TimeNow> alarmTimes;
	private Vector<String> alarmStrings;
	private AlarmList alarmList;
	private Image ringImage;
	private boolean ring;
	private int counter;

	public Alarms() {
		alarmTimes = new ArrayList<TimeNow>();
		alarmStrings = new Vector<String>();
		alarmList = new AlarmList(this);
		ring = false;
		ringImage = null;
		counter=0;
		try {
			ringImage = ImageIO.read(new File("alarmRing.png"));
		} catch (IOException e) {
		}
	}

	public void add(TimeNow time) {
		alarmTimes.add(time);
		alarmStrings.add(time.toString());
		alarmList.addAlarmToList();
		counter++;
	}

	public boolean getRing() {
		return ring;
	}

	public int getCounter(){
		return counter;
	}

	public Vector<String> getAlarmStrings() {
		return alarmStrings;
	}

	public void checkIfCurrentAlarms(TimeNow time) {
		TimeNow temp;
		for (int i = 0; i < alarmTimes.size(); i++) {
			temp = alarmTimes.get(i);
			if (temp.equals(time)) {
				ring = true;
				if (time.getSeconds() == 59) {
					alarmTimes.remove(temp);
					alarmList.remove(i);
					counter--;
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

/*	public Graphics paint(Graphics g, TimeNow time) {
		checkIfCurrentAlarms(time);
		if (ring) {
			int size = 200;
			g.drawImage(ringImage, Coordinates.radius + Coordinates.clockMargin - size / 2, Coordinates.radius
					+ Coordinates.clockMargin - size / 2, size, size, null);
		}
		return g;
	}*/

	public JList getAlarmList() {
		return alarmList;
	}
}