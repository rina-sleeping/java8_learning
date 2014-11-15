package ch6.ex03;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

class Counter {

	AtomicLong sum1 = new AtomicLong(0);
	LongAdder sum2 = new LongAdder();

	void addByAtomicLong(long add) {
		sum1.addAndGet(add);
	}

	long getByAtomicLong() {
		return sum1.get();
	}

	void addByLongAdder(long add) {
		sum2.add(add);
	}

	void addByLongAdder2(long add) {
		sum2.increment();
	}

	long getByLogAdder() {
		return sum2.sum();
	}
}
