package tick_tock;

import java.util.ArrayList;
import java.util.Vector;

public class Alarms {
	private ArrayList<TimeNow> alarmTimes;
	private Vector<String> alarmStrings;
	private AlarmList alarmList;
	private boolean ring;
	private int counter;

	public Alarms() {
		alarmTimes = new ArrayList<TimeNow>();
		alarmStrings = new Vector<String>();
		alarmList = new AlarmList(alarmStrings);
		ring = false;
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

	public AlarmList getAlarmList() {
		return alarmList;
	}
}