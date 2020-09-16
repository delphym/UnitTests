package net.delphym.unittests.pragmastic.junitparams;

import lombok.Data;

@Data
public class Person {
	private int age;
	private boolean adult;

	public Person(int age) {
		setAge(age);
	}

	public boolean isAdult() {
		return age > 17;
	}

	@Override
	public String toString() {
		return "Person of age: " + age;
	}
}
