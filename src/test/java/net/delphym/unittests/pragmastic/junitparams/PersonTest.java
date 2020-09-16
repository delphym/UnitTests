package net.delphym.unittests.pragmastic.junitparams;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

@Log4j2
//@RunWith(JUnit4ClassRunner.class)
@RunWith(JUnitParamsRunner.class)
public class PersonTest {

	@Test
	@Parameters( {  "17, false",
					"22, true",
					"18, true",
					"15, false" } )
	public void personIsAdult(int age, boolean valid) {
		log.info("age: {}, is adult? {}", age,valid);
		assertThat(new Person(age).isAdult(), is(valid));
	}

	@Test
	@Parameters(method = "adultValues")
	public void personIsAdultValues(int age, boolean valid) {
		Person p = new Person(age);
		log.info("{}, is adult? {}", p, valid);
		assertEquals(valid, p.isAdult());
	}
	private Object[] adultValues() {
		return new Object[] {
				new Object[] {13, false},
				new Object[] {17, false},
				new Object[] {18, true},
				new Object[] {22, true}
		};
	}


	@Test
	@Parameters //(method = "parametersForIsAdultTest") //no need to use method attribute
	public void isAdultTest(Person person, boolean valid) throws Exception {
		log.info("{} is adult? {}", person, valid);
		assertThat(person.isAdult(), is(valid));
	}
	private Object[] parametersForIsAdultTest() { //if this method has prefix "parametersFor" and suffix the name of method for which it provides the params
		return new Object[] {
				new Object[] {new Person(12), false},
				new Object[] {new Person(17), false},
				new Object[] {new Person(18), true},
				new Object[] {new Person(21), true}
		};
	}

}
