package com.crossman;

import java.util.Iterator;
import java.util.function.Function;

public final class Iterables {
	private Iterables() {}

	public static <T,U> Iterable<U> map(Iterable<T> src, Function<T,U> fn) {
		return new Iterable<U>() {
			@Override
			public Iterator<U> iterator() {
				final Iterator<T> it = src.iterator();
				return new Iterator<U>() {
					@Override
					public boolean hasNext() {
						return it.hasNext();
					}

					@Override
					public U next() {
						final T next = it.next();
						if (next == null) {
							return null;
						}
						return fn.apply(next);
					}
				};
			}
		};
	}
}
