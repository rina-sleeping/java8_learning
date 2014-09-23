package ch1.ex08;

import java.util.ArrayList;
import java.util.List;

public class Ex8 {

	public static void main(String[] args) {
		String[] names = { "Peter", "Paul", "Mary" };
		List<Runnable> runners = new ArrayList<>();
		for (String name : names)
			runners.add(() -> System.out.println(name));// 正当なコード。異なる値をキャプチャする。

		for (Runnable runner : runners)
			runner.run();

		List<Runnable> runners2 = new ArrayList<>();

		for (int i = 0; i < names.length; i++) {
			String name = names[i];
			// 一旦String型で保存しないといけない。ラムダ式内ではnames[i]で参照できない。
			// 「Local variable i defined in an enclosing scope must be final or
			// effectively final」と出る。
			// System.out.println(i)も同様にエラーが出る。iがラムダ式外でも変更されているとp.13の制約にあったっているらしい
			runners2.add(() -> System.out.println(name));
		}
		for (Runnable runner : runners2)
			runner.run();
	}

}
