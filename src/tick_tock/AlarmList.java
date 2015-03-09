package tick_tock;

import java.util.Vector;
import javax.swing.DefaultListModel;
import javax.swing.JList;

public class AlarmList extends JList<String> {

	private static final long serialVersionUID = 1L;
	private DefaultListModel<String> alarmStringsModel;
	private Vector<String> alarmStrings;

	public AlarmList(Alarms alarms) {
		alarmStrings = alarms.getAlarmStrings();
		alarmStringsModel = new DefaultListModel<String>();
		for (String temp : alarmStrings) {
			alarmStringsModel.addElement(temp);
		}
		this.setModel(alarmStringsModel);
	}

	public void addAlarmToList() {
		String newAlarm = alarmStrings.get(alarmStrings.size() - 1);
		alarmStringsModel.addElement(newAlarm);
	}
	public void remove(int i){
		alarmStringsModel.removeElementAt(i);
	}

}
