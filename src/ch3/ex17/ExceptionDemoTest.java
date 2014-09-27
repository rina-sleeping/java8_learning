package ch3.ex17;

import java.util.concurrent.locks.ReentrantLock;

import org.junit.Test;

public class ExceptionDemoTest {
	private static int count = 0;
	private static ReentrantLock myLock = new ReentrantLock();

	public static void func(Throwable e) {
		System.out.println("Yikes: " + e);
		myLock.lock();
		count++;
		myLock.unlock();
	}

	@Test
	public void test() {
		int[] arg = new int[1];
		ExceptionDemo.doInParallelAsync(() -> System.out.println(arg[2]),
				() -> System.out.println("Goodbye"), ExceptionDemoTest::func);

		while (true) {
			myLock.lock();
			if (ExceptionDemoTest.count == 1) {
				myLock.unlock();
				break;
			}
			myLock.unlock();
		}
	}

	@Test
	public void test2() {
		int[] arg = new int[1];
		ExceptionDemo.doInParallelAsync(() -> System.out.println("Hello"),
				() -> System.out.println(arg[2]), ExceptionDemoTest::func);

		while (true) {
			myLock.lock();
			if (ExceptionDemoTest.count == 1) {
				myLock.unlock();
				break;
			}
			myLock.unlock();
		}
	}

	@Test
	public void test3() {
		int[] arg = new int[1];
		ExceptionDemo.doInParallelAsync(() -> System.out.println(arg[1]),
				() -> System.out.println(arg[2]), ExceptionDemoTest::func);

		while (true) {
			myLock.lock();
			if (ExceptionDemoTest.count == 2) {
				myLock.unlock();
				break;
			}
			myLock.unlock();
		}
	}
}
