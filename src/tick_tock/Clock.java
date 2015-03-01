package tick_tock;

//coord for the clock numbers, face and hands
public class Clock {
private TimeNow time;
public Clock(){
	this.time = new TimeNow();
}

public TimeNow getTime(){
	return time;
}
}
