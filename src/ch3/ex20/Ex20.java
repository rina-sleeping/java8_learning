package ch3.ex20;

import java.util.List;
import java.util.Stack;
import java.util.function.Function;

class Ex20 {

	static <T, U> List<U> map(List<T> list, Function<T, U> f) {
		List<U> ret = new Stack<U>();
		for (T l : list) {
			ret.add(f.apply(l));
		}
		return ret;
	}
}
