package ch3.ex2;

import java.util.concurrent.locks.ReentrantLock;

public class Lock {
	public static void withLock(ReentrantLock myLock, Runnable runner) {
		myLock.lock();
		try {
			runner.run();
		} finally {
			myLock.unlock();
		}
	}
}
