package ch6.ex07;

import static org.junit.Assert.assertTrue;

import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;

import org.junit.Test;

public class Ex7Test {

	BiFunction<Entry<String, Long>, Entry<String, Long>, ? extends Entry<String, Long>> func = (
			e1, e2) -> e1.getValue() > e2.getValue() ? e1 : e2;

	@Test
	public void test() {
		ConcurrentHashMap<String, Long> map = new ConcurrentHashMap<String, Long>();
		Long max = 100000L;
		map.put("max", max);
		map.put("max2", max);
		map.put("a", 1L);
		Entry<String, Long> e;
		String key;

		e = map.reduceEntries(0, func);

		key = e.getKey();
		assertTrue(key == "max" || key == "max2");

		e = map.reduceEntries(4, func);

		key = e.getKey();
		assertTrue(key == "max" || key == "max2");
	}

	@Test
	public void test2() {
		ConcurrentHashMap<String, Long> map = new ConcurrentHashMap<String, Long>();
		map.put("a", 1L);
		Entry<String, Long> e;
		String key;

		e = map.reduceEntries(0, func);

		key = e.getKey();
		assertTrue(key == "a");

		e = map.reduceEntries(2, func);

		key = e.getKey();
		assertTrue(key == "a");
	}

	@Test
	public void test3() {
		ConcurrentHashMap<String, Long> map = new ConcurrentHashMap<String, Long>();

		Entry<String, Long> e = map.reduceEntries(0, func);

		assertTrue(e == null);
	}
}
