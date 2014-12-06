package ch6.ex08;

import java.util.Arrays;
import java.util.Date;
import java.util.Random;

public class Ex8 {

	public static void main(String[] args) {
		Random random = new Random(new Date().getTime());
		final int ADD = 100000;
		final int LOOP = 5;
		long start, end;

		int size = 0;
		for (int c = 0; c < 5; c++) {
			size += ADD;
			int[] data = new int[size];

			for (int i = 0; i < size; ++i) {
				data[i] = random.nextInt(size);
			}

			System.out.printf("lenght=%d\n", size);

			long sum = 0;
			for (int i = 0; i < LOOP; i++) {
				int[] tmp = data.clone();
				start = System.nanoTime();
				Arrays.parallelSort(tmp);
				end = System.nanoTime();
				sum += end - start;
			}
			System.out.println("Arrays.parallelSort\t:" + sum / LOOP + "[ns]");

			sum = 0;
			for (int i = 0; i < LOOP; i++) {
				int[] tmp = data.clone();
				start = System.nanoTime();
				Arrays.sort(tmp);
				end = System.nanoTime();
				sum += end - start;
			}

			System.out.println("Arrays.sort\t\t:" + sum / LOOP + "[ns]");

			System.out.println("----------------");
		}
	}
}
