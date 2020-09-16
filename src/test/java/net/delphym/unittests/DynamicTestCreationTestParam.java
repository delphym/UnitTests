package net.delphym.unittests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DynamicTestCreationTestParam {
	private static Stream<int[]> dataArrays() {
		return Stream.of(new int[][] { {1, 2, 2}, {5, 3, 15}, {121, 4, 484} });
	}

	private static Stream<Arguments> data() {
		return Stream.of(Arguments.of(1, 2, 2), Arguments.of(5, 3, 15), Arguments.of(121, 4, 484));
	}

	@DisplayName("Multiplication test with Arrays")
	@ParameterizedTest(name = "#{index} for {arguments}")
	@MethodSource("dataArrays")
	void testWithStringParameter(int[] data) {
		MyClass tester = new MyClass();
		int m1 = data[0];
		int m2 = data[1];
		int expected = data[2];
		assertEquals(expected, tester.multiply(m1, m2));
	}

	@DisplayName("Multiplication test with Arguments")
	@ParameterizedTest(name = "#{index} multiply: {0} x {1} = {2}")
	@MethodSource("data")
	void testWithStringParameter(int m1, int m2, int expected) {
		MyClass tester = new MyClass();
		assertEquals(expected, tester.multiply(m1, m2));
	}

	@DisplayName("Multiplication test with CSVSource")
	@ParameterizedTest(name = "#{index} multiply: {0} x {1} = {2}")
	@CsvSource({"1,2,2", "3,5,15", "121,4,484"})
	void testWithStringParameterCSVSource(int m1, int m2, int expected) {
		MyClass tester = new MyClass();
		assertEquals(expected, tester.multiply(m1, m2));
	}
}
