package ch3.ex07;

import static org.junit.Assert.assertTrue;

import java.util.Comparator;

import org.junit.Test;

public class ComparatorCreatorTest {

	@Test
	public void test() {
		ComparatorCreator creator = new ComparatorCreator();
		Comparator<String> comp;

		comp = creator.create();
		assertTrue(comp.compare("a", "b") < 0);
		assertTrue(comp.compare("a", "a") == 0);
		assertTrue(comp.compare("b", "a") > 0);
		assertTrue(comp.compare("a", "A") > 0);
		assertTrue(comp.compare("A", "a") < 0);
		assertTrue(comp.compare("a", "a ") < 0);
		assertTrue(comp.compare("a", " a") > 0);
		assertTrue(comp.compare("a ", "a") > 0);
		assertTrue(comp.compare(" a", "a") < 0);

		comp = creator.create(Option.REVERSE);
		assertTrue(comp.compare("a", "b") > 0);
		assertTrue(comp.compare("a", "a") == 0);
		assertTrue(comp.compare("b", "a") < 0);
		assertTrue(comp.compare("a", "A") < 0);
		assertTrue(comp.compare("A", "a") > 0);
		assertTrue(comp.compare("a", "a ") > 0);
		assertTrue(comp.compare("a", " a") < 0);
		assertTrue(comp.compare("a ", "a") < 0);
		assertTrue(comp.compare(" a", "a") > 0);

		comp = creator.create(Option.IGNORE_CASE);
		assertTrue(comp.compare("a", "A") == 0);
		assertTrue(comp.compare("A", "a") == 0);

		comp = creator.create(Option.IGNORE_SPACE);
		assertTrue(comp.compare("a", "a ") == 0);
		assertTrue(comp.compare("a", " a") == 0);
		assertTrue(comp.compare("a ", "a") == 0);
		assertTrue(comp.compare(" a", "a") == 0);

		comp = creator.create(Option.IGNORE_CASE, Option.IGNORE_SPACE);
		assertTrue(comp.compare("ab cD e", " AB C dE") == 0);

		comp = creator.create(Option.REVERSE, Option.IGNORE_CASE,
				Option.IGNORE_SPACE);
		assertTrue(comp.compare("ab cD e", " AB C dE") == 0);
		assertTrue(comp.compare("ab cD e", " AB C dEf") > 0);
	}
}
