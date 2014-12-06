package ch6.ex09;

import java.util.Arrays;

/**
 * This Matrix represents matrix, its row length is equal to its column length.
 */
class Matrix {
	private int[][] e;

	Matrix(int len) {
		e = new int[len][len];
	}

	/**
	 * this matrix equals [[e11 e12], [e21 e22]]matrix.
	 * 
	 * @param e11
	 * @param e12
	 * @param e21
	 * @param e22
	 */
	Matrix(int[][] e) {
		int len = e.length;
		for (int x = 0; x < e.length; x++) {
			if (len != e[x].length) {
				throw new RuntimeException();
			}
		}
		this.e = new int[len][len];
		for (int x = 0; x < e.length; x++) {
			this.e[x] = Arrays.copyOf(e[x], len);
		}
	}

	static Matrix multiply(Matrix m1, Matrix m2) {
		int len = m1.e.length;
		Matrix result = new Matrix(len);
		int[] val = new int[len];

		for (int x = 0; x < len; x++) {
			for (int y = 0; y < len; y++) {
				int ytmp = y;
				int xtmp = x;
				Arrays.parallelSetAll(val, i -> m1.e[xtmp][i] * m2.e[i][ytmp]);
				Arrays.parallelPrefix(val, (a, b) -> a + b);

				result.e[x][y] = val[len - 1];
			}
		}

		return result;
	}

	int[][] getE() {
		int[][] val = new int[e.length][e.length];
		for (int x = 0; x < e.length; x++) {
			val[x] = Arrays.copyOf(e[x], e.length);
		}
		return val;
	}
}
