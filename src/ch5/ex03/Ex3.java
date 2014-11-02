package ch5.ex03;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjuster;
import java.util.function.Predicate;

public class Ex3 {
	public static TemporalAdjuster next(Predicate<LocalDate> condition) {
		return (w -> {
			LocalDate result = (LocalDate) w;
			do {
				result = result.plusDays(1);
			} while (!condition.test(result));
			return result;
		});
	}
}
