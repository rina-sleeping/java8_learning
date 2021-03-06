package ch3.ex24;

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

	<U> Pair<U> flatMap(Function<? super T, Pair<U>> mapper) {
		return mapper.apply(this.one);// 適用するものが2つあるため、FunctionでPairを作成できない
	}
}
