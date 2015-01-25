package ch8.ex06;

import java.util.Comparator;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;

public class Ex06 {

	public static int compare(Point2D a, Point2D b) {
		return Comparator.comparing(Point2D::getX).thenComparing(Point2D::getY)
				.compare(a, b);
	}

	public static int compare(Rectangle2D a, Rectangle2D b) {
		return Comparator.comparing(Rectangle2D::getMinX)
				.thenComparing(Rectangle2D::getMaxX)
				.thenComparing(Rectangle2D::getMinY)
				.thenComparing(Rectangle2D::getMaxY).compare(a, b);
	}
}
