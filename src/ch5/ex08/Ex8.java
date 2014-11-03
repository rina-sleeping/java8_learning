package ch5.ex08;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
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

public class Ex8 {
	public static ZoneOffset[] getOffsetsOfNow() {
		CollectingZoneOffset collector = new CollectingZoneOffset(
				LocalDateTime.now());
		return ZoneId.getAvailableZoneIds().stream().collect(collector);
	}

	private static class CollectingZoneOffset implements
			Collector<String, List<ZoneOffset>, ZoneOffset[]> {
		private LocalDateTime now;

		CollectingZoneOffset(LocalDateTime now) {
			this.now = now;
		}

		@Override
		public BiConsumer<List<ZoneOffset>, String> accumulator() {
			return (a, t) -> {
				a.add(ZonedDateTime.of(now, ZoneId.of(t)).getOffset());
			};
		}

		@Override
		public Set<Collector.Characteristics> characteristics() {
			Set<Collector.Characteristics> chars = new LinkedHashSet<Collector.Characteristics>();
			chars.add(Characteristics.CONCURRENT);
			return chars;
		}

		@Override
		public BinaryOperator<List<ZoneOffset>> combiner() {
			return (a1, a2) -> {
				a1.addAll(a2);
				return a1;
			};
		}

		@Override
		public Function<List<ZoneOffset>, ZoneOffset[]> finisher() {
			return (a -> {
				return a.toArray(new ZoneOffset[0]);
			});
		}

		@Override
		public Supplier<List<ZoneOffset>> supplier() {
			return () -> new Stack<ZoneOffset>();
		}

	}
}
