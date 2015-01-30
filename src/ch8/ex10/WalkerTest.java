package ch8.ex10;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.Test;

public class WalkerTest {

	@Test
	public void test() {
		try {
			Path[] result = Walker.search("./src/data/src", "transient",
					"volatile");
			assertEquals(513, result.length);
		} catch (IOException e) {
			e.printStackTrace();
			fail(e.toString());
		}

	}
}
