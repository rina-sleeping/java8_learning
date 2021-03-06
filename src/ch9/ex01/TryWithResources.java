package ch9.ex01;

import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.Scanner;

public class TryWithResources {
	public static void main(String[] args) {
		Scanner in = null;
		PrintWriter out = null;
		try {
			in = new Scanner(Paths.get("./src/ch8/ex09/test.txt"));
			out = new PrintWriter("./src/ch9/ex01/out.txt");
			while (in.hasNext())
				out.println(in.next().toLowerCase());

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (out != null) {
					out.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
}
