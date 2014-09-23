package ch1.ex01;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class Ex1Test {

	@Test
	public void test() {
		String[] strings = { "aaaa", "bb", "ccccccc", "dddddd" };

		String[] result = Ex1.showThreadName(strings);

		assertEquals(result[0], "bb");
		assertEquals(result[1], "aaaa");
		assertEquals(result[2], "dddddd");
		assertEquals(result[3], "ccccccc");
	}

}
