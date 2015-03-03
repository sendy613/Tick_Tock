package tick_tock;

import java.util.ArrayList;

public class Alarms {
	private ArrayList<TimeNow> list;

	public Alarms() {
		this.list = new ArrayList<TimeNow>();
	}

	public boolean checkIfCurrentAlarms(TimeNow time) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).equals(time)) {
				return true;
			}
		}
		return false;
	}
}