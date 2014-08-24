package ch2.ex1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;

import org.junit.Test;

public class Ex1Test {

	@Test
	public void test() {
		try {
			int answer = Ex1.countWordsByForLoop();
			int result = Ex1.countWords();

			assertEquals(answer, result);

		} catch (IOException e) {
			e.printStackTrace();
			fail("Exception occured");
		}

	}
}
