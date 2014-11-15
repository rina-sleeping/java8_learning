package ch6.ex01;

import java.util.concurrent.atomic.AtomicReference;

public class MaxLengthString {

	AtomicReference<String> max = new AtomicReference<String>("");

	public void update(String string) {
		max.updateAndGet(s -> {
			if (s.length() < string.length()) {
				return string;
			} else {
				return s;
			}
		});
	}

	public String getMaxLengthString() {
		return max.get();
	}
}
