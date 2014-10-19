package ch4.ex5;

import java.util.function.BiFunction;
import java.util.function.Function;

import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

class Observer {

	public static <T, R> ObservableValue<R> observe(Function<T, R> f,
			ObservableValue<T> t) {
		return new ObservableValue<R>() {
			@Override
			public void addListener(InvalidationListener listener) {
				t.addListener(listener);

			}

			@Override
			public void removeListener(InvalidationListener listener) {
				t.removeListener(listener);

			}

			@SuppressWarnings("unchecked")
			@Override
			public void addListener(ChangeListener<? super R> listener) {
				t.addListener((ChangeListener<? super T>) listener);
			}

			@Override
			public R getValue() {
				return f.apply(t.getValue());
			}

			@SuppressWarnings("unchecked")
			@Override
			public void removeListener(ChangeListener<? super R> listener) {
				t.removeListener((ChangeListener<? super T>) listener);
			}
		};
	}

	public static <T, U, R> ObservableValue<R> observe(BiFunction<T, U, R> f,
			ObservableValue<T> t, ObservableValue<U> u) {
		return new ObservableValue<R>() {
			@Override
			public void addListener(InvalidationListener listener) {
				t.addListener(listener);
				u.addListener(listener);
			}

			@Override
			public void removeListener(InvalidationListener listener) {
				t.removeListener(listener);
				u.removeListener(listener);

			}

			@SuppressWarnings("unchecked")
			@Override
			public void addListener(ChangeListener<? super R> listener) {
				t.addListener((ChangeListener<? super T>) listener);
				u.addListener((ChangeListener<? super U>) listener);
			}

			@Override
			public R getValue() {
				System.out.println(u.getValue());
				return f.apply(t.getValue(), u.getValue());
			}

			@SuppressWarnings("unchecked")
			@Override
			public void removeListener(ChangeListener<? super R> listener) {
				t.removeListener((ChangeListener<? super T>) listener);
				u.removeListener((ChangeListener<? super U>) listener);

			}
		};
	}
}
