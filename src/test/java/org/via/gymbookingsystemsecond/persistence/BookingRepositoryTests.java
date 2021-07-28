package org.via.gymbookingsystemsecond.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.via.gymbookingsystemsecond.util.RandomUtils.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.via.gymbookingsystemsecond.domain.Account;
import org.via.gymbookingsystemsecond.domain.Booking;
import org.via.gymbookingsystemsecond.domain.Gym;

@SpringBootTest
public class BookingRepositoryTests {

	@Autowired private BookingRepository repo;

	@Autowired private GymRepository gymRepo;
	@Autowired private AccountRepository accRepo;

	@Test
	void testCreateBooking() {
		Account a = accRepo.add(randAccount());
		Gym g = gymRepo.add(randGym());

		Booking b = new Booking(a, g,
				LocalDate.now(),
				getRandom().nextInt(24));

		Booking resp = repo.add(b);

		assertEquals(b.getAccount(), resp.getAccount());
		assertEquals(b.getGym(), resp.getGym());
		assertEquals(b.getDate(), resp.getDate());
		assertEquals(b.getHour(), resp.getHour());
	}

	@Test
	void testFindByAccountIdAndGymId() {
		Account a = accRepo.add(randAccount());
		Gym g = gymRepo.add(randGym());

		Booking b = repo.add(new Booking(
					a, g, LocalDate.now(), getRandom().nextInt(24)));

		List<Booking> resp = repo.findByAccountIdAndGymId(
				b.getAccount().getId(),
				b.getGym().getId());

		assertTrue(resp.contains(b));
	}

	@Test
	void testFindByAccountIdAndGymIdAndDate() {
		Account a = accRepo.add(randAccount());
		Gym g = gymRepo.add(randGym());

		Booking b = repo.add(new Booking(
					a, g, LocalDate.now(), getRandom().nextInt(24)));

		System.out.println("################################################");

		List<Booking> resp = repo.findByAccountIdAndGymIdAndDate(
				b.getAccount().getId(),
				b.getGym().getId(),
				b.getDate());

		System.out.println("################################################");
		System.out.println(b);
		System.out.println("################################################");
		System.out.println(resp);
		System.out.println("################################################");

		assertTrue(resp.contains(b));
	}

	@Test
	void testFindByGymIdAndDateAndHour() {
		Account a = accRepo.add(randAccount());
		Gym g = gymRepo.add(randGym());

		Booking b = repo.add(new Booking(
					a, g, LocalDate.now(), getRandom().nextInt(24)));

		List<Booking> resp = repo.findByGymIdAndDateAndHour(
				b.getGym().getId(),
				b.getDate(),
				b.getHour());

		assertTrue(resp.contains(b));
	}

	@Test
	void testCountByGymIdAndDateAndHour() {
		Account a = accRepo.add(randAccount());
		Gym g = gymRepo.add(randGym());

		Booking b = repo.add(new Booking(
					a, g, LocalDate.now(), getRandom().nextInt(24)));

		int resp = repo.countByGymIdAndDateAndHour(
				b.getGym().getId(),
				b.getDate(),
				b.getHour());

		List<Booking> all = repo.findByGymIdAndDateAndHour(
				b.getGym().getId(),
				b.getDate(),
				b.getHour());

		assertEquals(all.size(), resp);
	}
}
