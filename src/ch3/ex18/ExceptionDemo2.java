package ch3.ex18;

import java.util.function.Function;

interface CallableFunction<T, U> {
	U call(T t) throws Exception;
}

public class ExceptionDemo2 {

	public static <T, U> Function<T, U> unchecked(CallableFunction<T, U> f) {
		return (input) -> {
			try {
				return f.call(input);
			} catch (Exception e) {
				throw new RuntimeException(e);
			} catch (Throwable t) {
				throw t;
			}
		};
	}
}
