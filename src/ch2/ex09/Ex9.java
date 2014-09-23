package ch2.ex09;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Stream;

public class Ex9 {
	public static <T> ArrayList<T> zip1(Stream<ArrayList<T>> array) {
		Optional<ArrayList<T>> result = array.reduce((x, y) -> {
			ArrayList<T> tmp = new ArrayList<T>();
			tmp.addAll(x);
			tmp.addAll(y);
			return tmp;
		});

		if (result.isPresent()) {
			return result.get();
		} else {
			return new ArrayList<T>(0);
		}
	}

	public static <T> ArrayList<T> zip2(Stream<ArrayList<T>> array) {
		return array.reduce(new ArrayList<T>(), (x, y) -> {
			x.addAll(y);
			return x;
		});
	}

	public static <T> ArrayList<T> zip3(Stream<ArrayList<T>> array) {
		return array.reduce(new ArrayList<T>(), (x, y) -> {
			x.addAll(y);
			return x;
		}, (x, y) -> {
			x.addAll(y);
			return x;
		});
	}
}
