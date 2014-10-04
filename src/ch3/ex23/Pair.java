package ch3.ex23;

import java.util.function.Function;

class Pair<T> {
	T one;
	T other;

	Pair(T one, T other) {
		this.one = one;
		this.other = other;
	}

	<U> Pair<U> map(Function<? super T, ? extends U> mapper) {
		mapper.apply(one);
		return new Pair<U>(mapper.apply(one), mapper.apply(other));
	}
}
