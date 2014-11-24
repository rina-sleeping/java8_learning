package ch6.ex04;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class Ex4Test {

	@Test
	public void test() {
		Ex4 test = new Ex4();

		test.add(0);
		test.add(1);
		test.add(-3);
		test.add(400);

		assertEquals(400, test.max());
		assertEquals(-3, test.min());
	}
}
