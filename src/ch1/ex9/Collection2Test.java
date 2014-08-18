package ch1.ex9;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class Collection2Test {

	private class ArrayList2<T> extends ArrayList<T> implements Collection2<T> {
		private static final long serialVersionUID = 1L;
	}

	@Test
	public void test() {
		ArrayList2<String> list = new ArrayList2<>();
		list.add("abc");
		list.add("abcde");
		list.add("a");
		list.add("abcdefg");

		List<String> expected = new ArrayList<>();

		list.forEachIf(expected::add, (e) -> e.length() > 3);

		assertEquals(2, expected.size());
	}
}
