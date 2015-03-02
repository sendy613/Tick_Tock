package tick_tock;

//coordinates for the clock numbers, face and hands

public class Clock {
	private TimeNow time;

	public Clock() {
		time = new TimeNow();
	}

	public TimeNow getTime() {
		return time;
	}
}
