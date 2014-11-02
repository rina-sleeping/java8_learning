package ch5.ex05;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class LivingDuration {
	public static long calc(LocalDate birthday) {
		return birthday.until(LocalDate.now(), ChronoUnit.DAYS);
	}
}
