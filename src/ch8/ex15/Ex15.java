package ch8.ex15;

import java.util.List;

public class Ex15 {

	public static void main(String[] args) {
		if (args.length != 2) {
			System.out.println("usage: java Ex15.class hoge .");
			return;
		}

		List<String> result = new Grepper(args[0], args[1]).grep();
		for (String r : result) {
			System.out.println(r);
		}
	}
}
