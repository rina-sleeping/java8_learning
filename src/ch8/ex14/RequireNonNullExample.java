package ch8.ex14;

import java.util.Objects;

public class RequireNonNullExample {

	public static void main(String[] args) {
		funcRequiredNonNull(null);
		funcRequiredNonNull1(null);
	}

	public static void funcRequiredNonNull(String str) {
		String s = Objects.requireNonNull(str, "require non null param");
		System.out.println(s);
	}

	public static void funcRequiredNonNull1(String str) {
		if (str == null) {
			System.out.println("require non null param");
			return;
		}
		System.out.println(str);
	}
}
