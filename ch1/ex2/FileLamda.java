package ex2;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileLamda {
	public static File[] getSubDiectories(String path) {
		File root = new File(path);
		List<File> list = new ArrayList<File>();

		addSubDirectories(root, list);
		return list.toArray(new File[0]);
	}

	private static void addSubDirectories(File root, List<File> list) {
		File[] dirs = root.listFiles((File file) -> file.isDirectory());
		if (dirs.length == 0) {
			return;
		}

		for (File f : dirs) {
			list.add(f);
			addSubDirectories(f, list);
		}
		return;
	}
}
