package ch6.ex09;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class MatrixTest {

	@Test
	public void test() {
		Matrix a = new Matrix(new int[][] { { 1, 0 }, { 1, 0 } });
		Matrix b = new Matrix(new int[][] { { 1, 0 }, { 1, 0 } });

		int[][] result = Matrix.multiply(a, b).getE();

		assertEquals(1, result[0][0]);
		assertEquals(0, result[0][1]);
		assertEquals(1, result[1][0]);
		assertEquals(0, result[1][1]);
	}

	@Test
	public void testFibo() {
		Matrix a = new Matrix(new int[][] { { 1, 1 }, { 1, 0 } });
		Matrix tmp = new Matrix(new int[][] { { 1, 1 }, { 1, 0 } });

		int f1 = 1, f2 = 1, f;
		for (int i = 0; i < 5; i++) {
			tmp = Matrix.multiply(tmp, a);

			int[][] result = tmp.getE();
			f = f1 + f2;
			assertEquals(f, result[0][0]);
			f1 = f2;
			f2 = f;
		}
	}

	@Test
	public void testException() {
		boolean flag = false;
		try {
			new Matrix(new int[][] { { 1, 1, 1 }, { 1, 0 } });
		} catch (RuntimeException e) {
			flag = true;
		}

		assertTrue(flag);
	}
}
