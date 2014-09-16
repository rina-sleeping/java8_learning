package ch3.ex2;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.concurrent.locks.ReentrantLock;

import org.junit.Test;

public class LockTest {

	@Test
	public void testLocked() {
		ReentrantLock myLock = new ReentrantLock();

		new Thread(() -> {
			Lock.withLock(myLock, () -> {
				while (true) {
				}
			});
		}).start();

		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		assertTrue(myLock.isLocked());

	}

	@Test
	public void testUnlocked() {
		ReentrantLock myLock = new ReentrantLock();

		Thread t = new Thread(() -> {
			Lock.withLock(myLock, () -> {
			});
		});
		t.start();

		try {
			t.join();
		} catch (InterruptedException e) {
			fail();
		}
	}
}
