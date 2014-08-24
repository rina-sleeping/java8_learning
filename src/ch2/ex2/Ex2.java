package ch2.ex2;

import java.util.stream.Stream;

public class Ex2 {

	public void Filter5() {
		Stream<String> words = Stream.of("dfw", "1st_found", "2nd_found", "a",
				"3rd_found", "bbb", "4th_found", "5th_found", "svaf",
				"do_not_find");

		Stream<String> filtered = words.filter(s -> {
			System.out.println(s);
			return s.length() > 5;
		}).limit(5);

		filtered.count();
	}

	public static void main(String[] args) {
		new Ex2().Filter5();
	}
}
