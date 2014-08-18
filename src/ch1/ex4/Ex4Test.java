package ch1.ex4;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

public class Ex4Test {

	@Test
	public void test() {
		// prepare
		File dir = new File("./test");
		File dirA = new File("./test/a");
		File dirB = new File("./test/b");
		File dirC = new File("./test/c");
		File a = new File("./test/a.txt");
		File b = new File("./test/b.txt");
		File c = new File("./test/c.txt");

		File[] files = { b, dirC, c, a, dirA, dirB };
		File[] expected = { dirA, dirB, dirC, a, b, c };

		for (File f : expected) {
			if (f.getName().endsWith(".txt")) {
				try {
					f.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
					fail();
				}
			} else {
				f.mkdirs();
			}
		}

		// action
		Ex4.sortFiles(files);

		// check
		assertArrayEquals(expected, files);

		a.delete();
		b.delete();
		c.delete();
		dirA.delete();
		dirB.delete();
		dirC.delete();
		dir.delete();
	}
}
