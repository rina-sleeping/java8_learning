package ch6.ex06;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.Collection;
import java.util.Set;

import org.junit.Test;

public class WordsLoaderTest {

	@Test
	public void test() {
		WordsLoader wl = new WordsLoader();
		String[] files = new String[] { "./src/ch6/files/alice.txt",
				"./src/ch6/files/alice2.txt", "./src/ch6/files/alice3.txt",
				"./src/ch6/files/alice4.txt" };
		Collection<Set<File>> set = wl.load(files);

		assertTrue(set.size() > 0);

		set.forEach(t -> {
			for (String f : files) {
				File file = new File(f);
				assertTrue(t.contains(file));
			}
		});
	}
}
