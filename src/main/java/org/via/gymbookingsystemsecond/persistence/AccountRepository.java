package org.via.gymbookingsystemsecond.persistence;

import org.via.gymbookingsystemsecond.domain.Account;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface AccountRepository extends Repository<Account> {

	Optional<Account> findById(Long id);

	Optional<Account> findByEmail(String email);

	List<Account> findByName(String name);
}
