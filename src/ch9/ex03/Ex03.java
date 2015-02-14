package ch9.ex03;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.UnknownHostException;
import java.nio.file.Paths;
import java.util.Scanner;

public class Ex03 {
	public void process() throws IOException {
		// 共通のスーパークラスでthrowするのが良い。
		try {
			try (Scanner in = new Scanner(Paths.get("xxx"))) {

			}
		} catch (FileNotFoundException | UnknownHostException ex) {
			throw ex;
		}
	}
}
