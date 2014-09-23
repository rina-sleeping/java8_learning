package ch1.ex03;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

public class FileLamdaTest {

	@Test
	public void testLamda() {
		File dir = new File("./test");
		File a = new File("./test/a.txt");
		File b = new File("./test/b.txt");
		File c = new File("./test/c.prn");

		try {
			dir.mkdir();
			a.createNewFile();
			b.createNewFile();
			c.createNewFile();

			File[] list;
			list = FileLamda.getSubDiectoriesByLamda(dir.getPath(), "txt");

			assertNotNull(list);
			assertEquals(2, list.length);
			assertEquals(a, list[0]);
			assertEquals(b, list[1]);

			list = FileLamda.getSubDiectoriesByLamda(dir.getPath(), "prn");

			assertNotNull(list);
			assertEquals(1, list.length);
			assertEquals(c, list[0]);
		} catch (IOException e) {
			e.printStackTrace();
			fail("Exception is occured");
		} finally {
			a.delete();
			b.delete();
			c.delete();
			dir.delete();
		}
	}
}
