package ch1.ex2;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileLamda {
	public static File[] getSubDiectoriesByLamda(String path) {
		File root = new File(path);
		List<File> list = new ArrayList<File>();

		addSubDirectoriesByLamda(root, list);
		return list.toArray(new File[0]);
	}

	private static void addSubDirectoriesByLamda(File root, List<File> list) {
		File[] dirs = root.listFiles((File file) -> file.isDirectory());
		if (dirs.length == 0) {
			return;
		}

		for (File f : dirs) {
			list.add(f);
			addSubDirectoriesByLamda(f, list);
		}
		return;
	}
	
	public static File[] getSubDiectoriesByMethod(String path) {
		File root = new File(path);
		List<File> list = new ArrayList<File>();

		addSubDirectoriesByMethod(root, list);
		return list.toArray(new File[0]);
	}

	private static void addSubDirectoriesByMethod(File root, List<File> list) {
		File[] dirs = root.listFiles(File::isDirectory);
		if (dirs.length == 0) {
			return;
		}

		for (File f : dirs) {
			list.add(f);
			addSubDirectoriesByLamda(f, list);
		}
		return;
	}
}
