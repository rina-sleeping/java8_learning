package ch3.ex07;

import java.util.Comparator;

enum Option {
	REVERSE, IGNORE_CASE, IGNORE_SPACE,
}

public class ComparatorCreator {
	public Comparator<String> create(Option... options) {
		return (first, second) -> {
			boolean reverse = false;
			boolean ignore_case = false;
			for (Option o : options) {
				switch (o) {
				case REVERSE:
					reverse = true;
					break;
				case IGNORE_CASE:
					ignore_case = true;
					break;
				case IGNORE_SPACE:
					first = first.replaceAll("\\s", "");
					second = second.replaceAll("\\s", "");
					break;
				}
			}
			if (reverse) {
				return ignore_case ? second.compareToIgnoreCase(first) : second
						.compareTo(first);
			} else {
				return ignore_case ? first.compareToIgnoreCase(second) : first
						.compareTo(second);
			}
		};

	}
}
