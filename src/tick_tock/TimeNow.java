package tick_tock;

import java.sql.Time;

/*Time class - private int hours minutes seconds
 constructor gets hours minutes seconds from Java's time class. (.currTimeMillis)
 .equals() method
 increment method - has all the logic for time (if==60) minutes++ etc
 */

//everything starts with 0
public class TimeNow {
	private int hours;
	private int min;
	private int seconds;
	private Long currTime;

	public TimeNow() {
		currTime = System.currentTimeMillis();
		Time time = new Time(currTime);
		String s = time.toString();
		String[] array = s.split(":");
		this.hours = Integer.parseInt(array[0]);
		this.min = Integer.parseInt(array[1]);
		this.seconds = Integer.parseInt(array[2]);

	}

	public TimeNow(int hours, int min, int sec) {
		this.hours = hours;
		this.min = min;
		this.seconds = sec;
	}

	public int getHours() {
		return hours;
	}

	public void setHours(int hours) {
		this.hours = hours;
	}

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public int getSeconds() {
		return seconds;
	}

	public void setSeconds(int seconds) {
		this.seconds = seconds;
	}

	public Long getCurrTime() {
		return currTime;
	}

	public void setCurrTime(Long currTime) {
		this.currTime = currTime;
	}

	public boolean equalsHoursMin(TimeNow t) {
		if (t.getHours() == this.getHours() && t.getMin() == this.getMin()) {
			return true;
		}
		return false;
	}

	public void tick() {
		if (hours == 23 && min == 59 && seconds == 59) {
			hours = 0;
			min = 0;
			seconds = 0;
		} else if (min == 59 && seconds == 59) {
			hours++;
			min = 0;
			seconds = 0;
		} else if (seconds == 59) {
			min++;
			seconds = 0;
		} else {
			seconds++;
		}

	}

	// take out at end
	public String toString() {
		return (getHours() + ": " + getMin() + ":" + getSeconds());
	}

	public static void main(String args[]) {
		TimeNow now = new TimeNow();
		/*
		 * while(now.getMin()!=2){ System.out.println(now.hours+ " "+ now.min +
		 * " " + now.seconds); now.tick(); }
		 */
		// System.out.println(now.hours+ " "+ now.min + " " + now.seconds);
	}
}
