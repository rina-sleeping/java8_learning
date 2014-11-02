package ch5.ex06;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayDeque;
import java.util.Deque;

public class Friday13th {
	public static LocalDate[] cal(int century) {
		if (century < 0) {
			return new LocalDate[] {};
		}

		Deque<LocalDate> days = new ArrayDeque<LocalDate>();
		LocalDate day = LocalDate.of((century - 1) * 100 + 1, 1, 13);
		while (day.getYear() < century * 100 + 1) {
			if (day.getDayOfWeek() == DayOfWeek.FRIDAY) {
				days.add(day);
			}
			day = day.plusMonths(1);
		}
		return days.toArray(new LocalDate[0]);
	}
}
