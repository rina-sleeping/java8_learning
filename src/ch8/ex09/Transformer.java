package ch8.ex09;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Transformer {
	private Scanner in;

	Transformer(Scanner in) {
		this.in = in;
	}

	public Stream<String> words() {
		return StreamSupport.stream(
				Spliterators.spliteratorUnknownSize(Transformer.this.in,
						Spliterator.ORDERED | Spliterator.NONNULL), false);
	}

	public Stream<String> lines() {

		Iterator<String> iter = new Iterator<String>() {
			@Override
			public boolean hasNext() {
				return Transformer.this.in.hasNextLine();
			}

			@Override
			public String next() {
				if (hasNext()) {
					return Transformer.this.in.nextLine();
				} else {
					throw new NoSuchElementException();
				}
			}
		};
		return StreamSupport.stream(
				Spliterators.spliteratorUnknownSize(iter, Spliterator.ORDERED
						| Spliterator.NONNULL), false);

	}

	public Stream<Integer> toInt() {
		Iterator<Integer> iter = new Iterator<Integer>() {
			@Override
			public boolean hasNext() {
				while (!Transformer.this.in.hasNextInt()) {
					if (!Transformer.this.in.hasNext()) {
						break;
					}
					Transformer.this.in.next();
				}
				return Transformer.this.in.hasNextInt();
			}

			@Override
			public Integer next() {
				if (hasNext()) {
					return Transformer.this.in.nextInt();
				} else {
					throw new NoSuchElementException();
				}
			}
		};
		return StreamSupport.stream(
				Spliterators.spliteratorUnknownSize(iter, Spliterator.ORDERED
						| Spliterator.NONNULL), false);
	}

	public Stream<Double> toDouble() {
		Iterator<Double> iter = new Iterator<Double>() {
			@Override
			public boolean hasNext() {
				while (!Transformer.this.in.hasNextDouble()) {
					if (!Transformer.this.in.hasNext()) {
						break;
					}
					Transformer.this.in.next();
				}
				return Transformer.this.in.hasNextDouble();
			}

			@Override
			public Double next() {
				if (hasNext()) {
					return Transformer.this.in.nextDouble();
				} else {
					throw new NoSuchElementException();
				}
			}
		};
		return StreamSupport.stream(
				Spliterators.spliteratorUnknownSize(iter, Spliterator.ORDERED
						| Spliterator.NONNULL), false);
	}
}
