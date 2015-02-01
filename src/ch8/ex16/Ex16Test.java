package ch8.ex16;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

public class Ex16Test {

	@Test
	public void test() {
		List<Address> result = Ex16.analyzeData("src/ch8/ex16/testData.txt");
		assertEquals(2, result.size());
		assertEquals("city:Plymouth,state:WI,zipCode:53073", result.get(0)
				.toString());
		assertEquals("city:NewYork,state:NY,zipCode:200641111", result.get(1)
				.toString());
	}

}
