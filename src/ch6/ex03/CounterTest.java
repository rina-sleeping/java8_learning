package ch6.ex03;

import static org.junit.Assert.fail;

import org.junit.Test;

public class CounterTest {

	@Test
	public void test() {
		Counter counter = new Counter();
		long start, end;
		Thread[] ts = new Thread[1000];

		for (int i = 0; i < ts.length; i++) {
			ts[i] = new Thread(() -> {
				for (int c = 0; c < 10000; c++) {
					counter.addByAtomicLong(1);
				}
			});
		}

		start = System.nanoTime();
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
		end = System.nanoTime();
		System.out.println("AtomicLong\t\t\t:" + (end - start));

		for (int i = 0; i < ts.length; i++) {
			ts[i] = new Thread(() -> {
				for (int c = 0; c < 10000; c++) {
					counter.addByLongAdder(1);
				}
			});
		}

		start = System.nanoTime();
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
		end = System.nanoTime();
		System.out.println("LongAdder by add()\t\t:" + (end - start));

		for (int i = 0; i < ts.length; i++) {
			ts[i] = new Thread(() -> {
				for (int c = 0; c < 10000; c++) {
					counter.addByLongAdder2(1);
				}
			});
		}

		start = System.nanoTime();
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
		end = System.nanoTime();
		System.out.println("LongAdder by increment()\t:" + (end - start));
	}
}
