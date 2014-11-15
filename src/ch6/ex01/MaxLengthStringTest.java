package ch6.ex01;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

public class MaxLengthStringTest {

	@Test
	public void test() {
		String max = "aaaaaaaaaaaaaaaaaaaaa";

		MaxLengthString test = new MaxLengthString();

		Thread[] ts = new Thread[6];

		ts[0] = new Thread(() -> test.update("aaa"));
		ts[1] = new Thread(() -> test.update("aa"));
		ts[2] = new Thread(() -> test.update("a"));
		ts[3] = new Thread(() -> test.update("aaaaa"));
		ts[4] = new Thread(() -> test.update(max));
		ts[5] = new Thread(() -> test.update("a"));

		for (Thread t : ts) {
			t.start();
		}

		for (Thread t : ts) {
			try {
				t.join();
			} catch (InterruptedException e) {
				fail("join failed");
			}
		}

		assertEquals(max, test.getMaxLengthString());
	}
}
