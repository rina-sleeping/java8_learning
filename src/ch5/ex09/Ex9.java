package ch5.ex09;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class Ex9 {
	public static String[] getTimeZoneOfNowWithin1Hour() {
		CollectingZoneOffset collector = new CollectingZoneOffset(
				LocalDateTime.now());
		return ZoneId.getAvailableZoneIds().stream().collect(collector);
	}

	private static class CollectingZoneOffset implements
			Collector<String, List<String>, String[]> {
		private LocalDateTime now;

		CollectingZoneOffset(LocalDateTime now) {
			this.now = now;
		}

		@Override
		public BiConsumer<List<String>, String> accumulator() {
			return (a, t) -> {
				int secs = ZonedDateTime.of(now, ZoneId.of(t)).getOffset()
						.getTotalSeconds();
				if (Math.abs(secs) < 60 * 60) {
					a.add(t);
				}
			};
		}

		@Override
		public Set<Collector.Characteristics> characteristics() {
			Set<Collector.Characteristics> chars = new LinkedHashSet<Collector.Characteristics>();
			chars.add(Characteristics.CONCURRENT);
			return chars;
		}

		@Override
		public BinaryOperator<List<String>> combiner() {
			return (a1, a2) -> {
				a1.addAll(a2);
				return a1;
			};
		}

		@Override
		public Function<List<String>, String[]> finisher() {
			return (a -> {
				return a.toArray(new String[0]);
			});
		}

		@Override
		public Supplier<List<String>> supplier() {
			return () -> new Stack<String>();
		}

	}
}
