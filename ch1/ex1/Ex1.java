package ex1;

import java.util.Arrays;
import java.util.Comparator;

public class Ex1 {
	public static String[] showThreadName( String[] strings) {
		Comparator<String> comp = (first, second) -> {
			System.out.println(Thread.currentThread().getName());
			return Integer.compare(first.length(), second.length());
		};
		Arrays.sort(strings, comp);

		System.out.println(Thread.currentThread().getName());
		return strings;
	}
}
