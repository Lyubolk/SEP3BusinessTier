package org.via.gymbookingsystemsecond.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.via.gymbookingsystemsecond.util.RandomUtils.randAccount;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.via.gymbookingsystemsecond.domain.Account;

@SpringBootTest
public class AccountRepositoryTests {

	@Autowired
	private AccountRepository repo;

	@Test
	void testAddAccount() {
		Account acc = randAccount();

		Account resp = repo.add(acc);

		assertNotNull(resp.getId());
		assertEquals(acc.getEmail(), resp.getEmail());
		assertEquals(acc.getName(), resp.getName());
		assertEquals(acc.getPassword(), resp.getPassword());
	}

	@Test
	void testFindByEmail() {
		Account acc = repo.add(randAccount());

		Account resp = repo.findByEmail(acc.getEmail())
			.orElseThrow();

		assertEquals(acc, resp);
	}

	@Test
	void testFindByName() {
		Account acc = repo.add(randAccount());

		List<Account> resp = repo.findByName(acc.getName());

		assertTrue(resp.contains(acc));
	}
}
