package ch1.ex9;

import java.util.Collection;
import java.util.function.Consumer;
import java.util.function.Predicate;

public interface Collection2<T> extends Collection<T> {
	default void forEachIf(Consumer<T> action, Predicate<T> filter) {
		for (T t : this) {
			if (filter.test(t)) {
				action.accept(t);
			}
		}
	}
}
