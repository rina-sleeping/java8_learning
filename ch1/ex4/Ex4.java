package ex4;

import java.io.File;
import java.util.Arrays;

public class Ex4 {
	public static void sortFiles(File[] files) {
		Arrays.sort(files, (first, second) -> {
			if (first.isDirectory() && !second.isDirectory()) {
				return -1;
			}
			if (!first.isDirectory() && second.isDirectory()) {
				return 1;
			}
			return first.getName().compareTo(second.getName());
		});
	}
}
