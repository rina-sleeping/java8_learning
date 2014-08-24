package ch2.ex8;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public class Ex8 {
	public static <T> Stream<T> zip(Stream<T> first, Stream<T> second) {
		List<T> combined = new ArrayList<T>();

		Iterator<T> fIte = first.iterator();
		Iterator<T> sIte = second.iterator();

		while (fIte.hasNext() && sIte.hasNext()) {
			combined.add(fIte.next());
			combined.add(sIte.next());
		}

		return combined.stream();
	}
}
