package org.via.gymbookingsystemsecond.persistence;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.via.gymbookingsystemsecond.domain.Account;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.via.gymbookingsystemsecond.util.RandomUtils.randAccount;

@SpringBootTest
@AutoConfigureMockMvc
public class AccountRepositoryTests {

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private AccountRepository repo;

	@Test
	void testAddAccount() throws Exception {
		Account acc = randAccount();
		mockMvc.perform(post("/accounts/register")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(acc)));

		Account resp = repo.findByEmail(acc.getEmail()).orElseThrow();

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
