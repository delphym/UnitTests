package net.delphym.unittests;

		import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

@Log4j2
@RunWith(Parameterized.class)
public class ParameterizedTestUsingConstructor {

	private int m1;
	private int m2;
	private int result;

	public ParameterizedTestUsingConstructor(int p1, int p2, int res) {
		m1 = p1;
		m2 = p2;
		result = res;
	}

	// creates the test data
	@Parameters
	public static Collection<Object[]> data() {
		Object[][] data = new Object[][] { { 1 , 3, 3 }, { 5, 3, 15 }, { 121, 4, 484 } };
		return Arrays.asList(data);
	}


	@Test
	public void testMultiplyException() {
		MyClass tester = new MyClass();
		log.info("{} * {} = {}", m1, m2, result);
		assertEquals("Result", m1 * m2, result);
	}

}