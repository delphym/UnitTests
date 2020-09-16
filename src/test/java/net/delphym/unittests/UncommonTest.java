package net.delphym.unittests;


import lombok.Data;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class UncommonTest {

	@Test
	void groupedAssertions() {
		Address address = new Address();
		address.setFirstName("John");
//		address.setLastName("User");

		assertAll(
				() -> assertEquals("John", address.getFirstName()),
				() -> assertEquals("User", address.getLastName())		//it's gonna fail here
		);

	}

	@Test
	void timeoutNotExceeded() {
		final Service service = new Service();
		assertTimeout(Duration.ofMinutes(1), () -> service.doBackup());
	}

	@Test
	void timeoutNotExceededWithResult() {
		String actualResult = assertTimeout(Duration.ofSeconds(1), () -> {
			return null; //restService.request(request);
		});
		assertEquals(200, 200); //request.getStatus());
	}

	@Test
	void timeoutNotExceededWithResultPreemptively() {
		String actualResult = assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
			return null; //restService.request(request);
		});
		assertEquals(200, 200); //request.getStatus());
	}

	static class Service {
		@SneakyThrows
		public void doBackup() {
			wait(61000);
		}
	}

	@Data
	class Address {
		String streetName;
		int streetNumber;
		String city;
		String country;
		String firstName;
		String LastName;
	}
}
