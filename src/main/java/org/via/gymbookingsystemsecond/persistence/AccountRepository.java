package org.via.gymbookingsystemsecond.persistence;

import org.via.gymbookingsystemsecond.domain.Account;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends Repository<Account> {

	Optional<Account> findByEmail(String email);

	List<Account> findByName(String name);
}
