package ch2.ex08;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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

	public static <T> Stream<T> zip2(Stream<T> first, Stream<T> second) {
		if (!isFinite(first) && !isFinite(second)) {
			Iterator<T> fIte = first.iterator();
			Iterator<T> sIte = second.iterator();
			StreamSupplier<T> supplier = new StreamSupplier<T>(fIte, sIte);
			return Stream.generate(supplier::getNext);
		} else {
			return zip(first, second);
		}
	}

	public static <T> boolean isFinite(Stream<T> stream) {
		try {
			Field f = stream.getClass().getSuperclass().getSuperclass()
					.getDeclaredField("sourceSpliterator");
			f.setAccessible(true);
			Object splite = f.get(stream);

			Method mSize = splite.getClass().getMethod("getExactSizeIfKnown");
			mSize.setAccessible(true);
			Long size = (Long) mSize.invoke(splite);

			return size != -1;
		} catch (NoSuchMethodException | SecurityException
				| IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchFieldException e) {
			e.printStackTrace();
		}
		return false;
	}
}

class StreamSupplier<T> {
	private int count;
	private Iterator<T> fIte;
	private Iterator<T> sIte;

	StreamSupplier(Iterator<T> fIte, Iterator<T> sIte) {
		this.count = 0;
		this.fIte = fIte;
		this.sIte = sIte;
	}

	T getNext() {
		count++;
		if (!fIte.hasNext() || !sIte.hasNext()) {

		}
		if (count % 2 == 1) {
			return fIte.next();
		} else {
			return sIte.next();
		}
	}
}
