package ch9.ex08;

public class Point {
	private int x, y;

	Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int compareTo(Point other) {
		long diff = (long) x - other.x;
		if (diff != 0)
			return diff > 0 ? 1 : -1;

		diff = (long) y - other.y;
		if (diff == 0)
			return 0;
		return diff > 0 ? 1 : -1;
	}

	public int compareToUsingCompare(Point other) {
		int diff = Integer.compare(x, other.x); // No risk of overflow
		if (diff != 0)
			return diff;
		return Integer.compare(y, other.y);
	}
}
