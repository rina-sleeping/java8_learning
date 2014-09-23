package ch1.ex03;

import java.io.File;

public class FileLamda {
	public static File[] getSubDiectoriesByLamda(String pathDir, String extension) {
		File root = new File(pathDir);
		
		//extension‚ðƒLƒƒƒvƒ`ƒƒ
		return root.listFiles((File dir, String name) ->name.endsWith("."+extension));
	}
}
