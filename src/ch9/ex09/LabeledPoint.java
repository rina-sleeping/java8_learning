package ch9.ex09;

import com.google.common.base.Objects;

public class LabeledPoint {
	private String label;
	private int x;
	private int y;

	LabeledPoint(String label, int x, int y) {
		this.label = label;
		this.x = x;
		this.y = y;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LabeledPoint other = (LabeledPoint) obj;
		return Objects.equal(label, other.label) && x == other.x
				&& y == other.y;
	}

	public int hashCode() {
		return Objects.hashCode(label, x, y);
	}
}
