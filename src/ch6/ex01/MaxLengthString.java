package ch6.ex01;

import java.util.concurrent.atomic.AtomicReference;

public class MaxLengthString {

	AtomicReference<String> max = new AtomicReference<String>("");

	public void update(String string) {
		String old;
		do {
			old = max.get();
			if (old.length() >= string.length()) {
				return;
			}
		} while (max.compareAndSet(old, string));
	}

	public String getMaxLengthString() {
		return max.get();
	}
}
