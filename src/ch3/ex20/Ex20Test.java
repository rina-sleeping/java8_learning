package ch3.ex20;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Stack;

import org.junit.Test;

public class Ex20Test {

	@Test
	public void test() {
		// prepare
		List<Integer> list = new Stack<Integer>();
		for (int i = 0; i < 10; i++)
			list.add(i);

		// test
		List<String> result = Ex20.map(list, (i) -> Integer.toString(i));

		// check
		int i = 0;
		for (String s : result) {
			assertEquals(s, Integer.toString(i));
			i++;
		}
	}
}
