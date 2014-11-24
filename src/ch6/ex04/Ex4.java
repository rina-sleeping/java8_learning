package ch6.ex04;

import java.util.concurrent.atomic.LongAccumulator;

public class Ex4 {
	LongAccumulator max = new LongAccumulator((n1, n2) -> n1 > n2 ? n1 : n2, 0);
	LongAccumulator min = new LongAccumulator((n1, n2) -> n1 > n2 ? n2 : n1, 0);

	public void add(long value) {
		max.accumulate(value);
		min.accumulate(value);
	}

	public long max() {
		return max.get();
	}

	public long min() {
		return min.get();
	}
}
