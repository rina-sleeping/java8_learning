package ch3.ex09;

import java.lang.reflect.Field;
import java.util.Comparator;

public class Ex9 {
	public Comparator<Object> lexicographicComparator(String... fieldNames) {
		return (first, second) -> {
			for (String field : fieldNames) {
				try {
					Field fFirst = first.getClass().getDeclaredField(field);
					fFirst.setAccessible(true);
					Object oFirst = fFirst.get(first);
					Field fSecond = second.getClass().getDeclaredField(field);
					fSecond.setAccessible(true);
					Object oSecond = fSecond.get(second);
					if (!oFirst.equals(oSecond)) {
						return oFirst.hashCode() - oSecond.hashCode();
					}
				} catch (NoSuchFieldException | SecurityException
						| IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
					throw new ClassCastException();
				}
			}
			return 0;
		};
	}
}
