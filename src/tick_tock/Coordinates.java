package tick_tock;

public class Coordinates {

	public final static int radius = 225;
	public final static int clockMargin = 25;

	public int getX(int numOutOf60) {
		int degrees = numOutOf60 * 6;
		double radians = degrees * Math.PI / 180;
		double x = (radius + clockMargin) + radius * Math.cos(radians);
		return (int) x;
	}

	public int getY(int numOutOf60) {
		int degrees = numOutOf60 * 6;
		double radians = degrees * Math.PI / 180;
		double y = (radius + clockMargin) + radius * Math.sin(radians);
		return (int) y;
	}

}