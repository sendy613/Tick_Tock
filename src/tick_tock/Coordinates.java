package tick_tock;

public class Coordinates {

	private int[] xList;
	private int[] yList;

	public Coordinates() {
		xList = new int[] { 15, 16, 18, 19, 21, 22, 24, 25, 26, 27, 28, 29, 30,
				30, 30, 30, 30, 30, 30, 29, 28, 27, 26, 25, 24, 23, 21, 19, 18,
				16, 15, 14, 12, 11, 9, 8, 6, 5, 4, 3, 2, 1, 0, 0, 0, 0, 0, 0,
				1, 1, 2, 3, 4, 5, 6, 8, 9, 11, 12, 14 };
		yList = new int[] { 0, 0, 0, 0, 1, 2, 3, 4, 6, 6, 7, 8, 10, 11, 13, 15,
				16, 18, 20, 22, 23, 24, 25, 26, 27, 28, 29, 30, 30, 30, 30, 30,
				30, 30, 29, 29, 27, 26, 25, 24, 23, 22, 20, 18, 16, 15, 13, 12,
				11, 9, 7, 6, 5, 4, 3, 1, 1, 0, 0, 0 };
	}

	public int getX(int num) {
		return xList[num];
	}

	public int getY(int num) {
		return yList[num];
	}
}
