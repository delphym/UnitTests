package net.delphym.unittests;

import lombok.extern.log4j.Log4j2;
import org.junit.Rule;
import org.junit.Test;
import org.junit.function.ThrowingRunnable;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;
import static org.junit.Assume.assumeFalse;

@Log4j2

public class RuleExceptionTesterExample {

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Test
	public void throwsIllegalArgumentExceptionIfNegativeNumber() {
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage("Negative value not allowed");
		ClassToBeTested t = new ClassToBeTested();
		t.methodToBeTested(-1);
	}

//	@Ignore("This is not needed if we use assumeFalse for a conditional deactivation of the test")
	@Test
	public void testDynamicallyDisabled() {
		String osName = System.getProperty("os.name");
		log.info("OS name: {}", osName);

		assumeFalse(osName.contains("Mac OS X"));
		log.info("Test is not disabled as you're not running on Mac");
	}

	@Test
	public void newWayOfTestingThrowsIllegalArgumentException() {
		final ClassToBeTested t = new ClassToBeTested();

		IllegalArgumentException actualE = assertThrows(IllegalArgumentException.class, new ThrowingRunnable() {
			@Override
			public void run() throws Throwable {
				t.methodToBeTested(-1);
			}
		});

		assertEquals("Negative value not allowed", actualE.getMessage());
	}

	class ClassToBeTested {
		int someValueGreaterZero;
		public void methodToBeTested(int i) throws IllegalArgumentException {
			if (i <= 0) throw new IllegalArgumentException("Negative value not allowed");
			someValueGreaterZero = i;
		}
	}
}
