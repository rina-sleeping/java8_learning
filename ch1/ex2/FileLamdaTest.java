package ex2;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class FileLamdaTest {

	
	private class FileFilterImplement implements FileFilter{
		public boolean accept(File pathname){
			return pathname.isDirectory();
		}
	}
	
	public  File[] getSubDiectories(String path) {
		File root = new File(path);
		List<File> list = new ArrayList<File>();

		addSubDirectories(root, list);
		return list.toArray(new File[0]);
	}
	
	private void addSubDirectories(File root, List<File> list) {
		File[] dirs = root.listFiles(new FileFilterImplement());
		if (dirs.length == 0) {
			return;
		}

		for (File f : dirs) {
			list.add(f);
			addSubDirectories(f, list);
		}
		return;
	}
	
	
	File dir,dirA,dirB,dirC;
	File a,b,c;
	
	@Before
	public void setUp() throws Exception {
		this.dir = new File("ch1/ex2/test");
		this.dirA = new File("ch1/ex2/test/a");
		this.dirB = new File("ch1/ex2/test/a/b");
		this.dirC = new File("ch1/ex2/test/a/c");
		this.a =new File("ch1/ex2/test/a.txt");
		this.b = new File("ch1/ex2/test/b.txt");
		this.c = new File("ch1/ex2/test/a/c/c.txt");
		
		this.dir.mkdir();
		this.dirA.mkdir();
		this.dirB.mkdir();
		this.dirC.mkdir();
		this.a.createNewFile();
		this.b.createNewFile();
		this.c.createNewFile();
	}

	@After
	public void tearDown() throws Exception {
		a.delete();
		b.delete();
		c.delete();
		dirC.delete();
		dirB.delete();
		dirA.delete();
		dir.delete();
	}

	
	@Test
	public void testLamda() {
		File now = new File("ch1/ex2/test");
		File[] list = FileLamda.getSubDiectoriesByLamda(now.getPath());	
		File[] expected = getSubDiectories(now.getPath());
	
		assertNotNull(list);
		assertEquals(3,list.length);
		assertArrayEquals(expected,list);
	}
	
	@Test
	public void testMethod() {
		File now = new File("ch1/ex2/test");
		File[] list = FileLamda.getSubDiectoriesByMethod(now.getPath());	
		File[] expected = getSubDiectories(now.getPath());
	
		assertNotNull(list);
		assertEquals(3,list.length);
		assertArrayEquals(expected,list);
	}

}
