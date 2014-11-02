package ch5.ex04;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class Calendar {
	public static void main(String[] args) {
		if (args.length != 2) {
			System.out.println("[Usage] java Cal month year");
			System.out.println("[Example] java Cal 3 2013");
			return;
		}

		int month = Integer.valueOf(args[0]);
		int year = Integer.valueOf(args[1]);

		LocalDate day = LocalDate.of(year, month, 1);

		for (int i = DayOfWeek.MONDAY.getValue(); i < day.getDayOfWeek()
				.getValue(); i++) {
			System.out.printf("   ");
		}

		while (day.getMonthValue() == month) {
			System.out.printf("%2d ", day.getDayOfMonth());
			if (day.getDayOfWeek() == DayOfWeek.SUNDAY) {
				System.out.println();
			}
			day = day.plusDays(1);
		}
	}
}
