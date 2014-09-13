package ch3.ex1;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.logging.Handler;
import java.util.logging.Level;

import org.junit.Test;

public class LoggerIfTest {

	private boolean called = false;

	public void call() {
		called = true;
	}

	private boolean called() {
		boolean ret = called;
		called = false;
		return ret;
	}

	@Test
	public void test() {
		// prepare
		LoggerIf logger = new LoggerIf("ch3.ex1", null);

		int i = 10;
		int[] a = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };

		for (Handler h : logger.getHandlers())
			h.setLevel(Level.ALL);

		// Level==null
		logger.logIf(Level.FINEST, () -> {
			fail();
			return i == 10;
		}, () -> "a[10] = " + a[10]);

		// Level==low
		logger.setLevel(Level.INFO);
		logger.logIf(Level.FINEST, () -> {
			fail();
			return i == 10;
		}, () -> "a[10] = " + a[10]);

		// Level:equal, condition:true
		logger.setLevel(Level.FINEST);
		logger.logIf(Level.FINEST, () -> {
			call();
			return i == 10;
		}, () -> "a[10] = " + a[10]);
		assertTrue(called());

		// Level:high, condition:true
		logger.setLevel(Level.FINEST);
		logger.logIf(Level.FINEST, () -> {
			call();
			return i == 10;
		}, () -> "a[10] = " + a[10]);
		assertTrue(called());
	}
}
