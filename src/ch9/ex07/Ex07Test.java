package ch9.ex07;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;

public class Ex07Test {

	@Test
	public void test() {
		// prepare
		Path output = Paths.get("./src/ch9/ex07/out.txt");
		try {
			Files.delete(output);
		} catch (NoSuchFileException e) {
			// ok
		} catch (IOException e) {
			e.printStackTrace();
			fail("cannot prepare");
		}

		// test
		try {
			Ex07.parseWebPage("http://www.google.co.jp");
		} catch (IOException e) {
			e.printStackTrace();
			fail("Exception was occurred");
		}

		// verify
		assertTrue(Files.exists(output));

	}
}
