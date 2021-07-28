package org.via.gymbookingsystemsecond.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.via.gymbookingsystemsecond.util.RandomUtils.randGym;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.via.gymbookingsystemsecond.domain.Gym;

@SpringBootTest
public class GymRepositoryTests {

	@Autowired
	private GymRepository repo;

	@Test
	void testAddGym() {
		Gym gym = randGym();
		Gym resp = repo.add(gym);

		assertNotNull(resp);
		assertEquals(gym.getName(), resp.getName());
		assertEquals(gym.getHourlyLimit(), resp.getHourlyLimit());
	}
}
