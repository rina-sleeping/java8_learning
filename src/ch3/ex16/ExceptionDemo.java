package ch3.ex16;

import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class ExceptionDemo {
	public static <T> void doInOrderAsync(Supplier<T> first,
			BiConsumer<T, Throwable> second) {
		Thread t = new Thread() {
			public void run() {
				try {
					T result = first.get();
					second.accept(result, null);
				} catch (Throwable t) {
					second.accept(null, t);
				}
			}
		};
		t.start();
	}

	public static void main(String[] args) {
		doInOrderAsync(() -> args[0], (s, e) -> {
			if (e != null) {
				System.out.println("Yikes: " + e);
			} else {
				System.out.println(s);
			}
		});

	}
}
