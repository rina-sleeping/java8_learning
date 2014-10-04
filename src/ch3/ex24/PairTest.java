package ch3.ex24;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PairTest {

	@Test
	public void test() {
		// prepare
		Pair<Integer> pair = new Pair<Integer>(1, 2);

		// test
		Pair<String> result = pair.map((i) -> Integer.toString(i));

		// check
		assertEquals(result.one, "1");
		assertEquals(result.other, "2");
	}

	class Person {
		private String name;

		Person(String name) {
			this.name = name;
		}
	}

	class Employee extends Person {
		Employee(String name) {
			super(name);
		}
	}

	@Test
	public void testType() {
		// prepare

		Pair<String> pair = new Pair<String>("Alice", "Bob");

		// test
		Pair<Person> result = pair.map((name) -> new Employee(name));

		// check
		assertEquals(result.one.name, "Alice");
		assertEquals(result.other.name, "Bob");
	}
}
