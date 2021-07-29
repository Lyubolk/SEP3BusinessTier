package org.via.gymbookingsystemsecond.persistence.hateoas;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;
import org.via.gymbookingsystemsecond.domain.Account;
import org.via.gymbookingsystemsecond.persistence.AccountRepository;

import java.util.List;
import java.util.Optional;

@Component
public class HateoasAccountRepository extends HateoasRepository<Account> implements AccountRepository {

	public HateoasAccountRepository() {
		super("accounts",
				new ParameterizedTypeReference<EntityModel<Account>>() {},
				new ParameterizedTypeReference<CollectionModel<EntityModel<Account>>>() {});
	}

	@Override
	public Optional<Account> findById(Long id) {
		return getItem(url("" + id));
	}

	@Override
	public Optional<Account> findByEmail(String email) {
		UriComponentsBuilder uriBuilder = UriComponentsBuilder
			.fromHttpUrl(url("search/findByEmail"))
			.queryParam("email", email);

		return getItem(uriBuilder.toUriString());
	}

	@Override
	public List<Account> findByName(String name) {
		UriComponentsBuilder uriBuilder = UriComponentsBuilder
			.fromHttpUrl(url("search/findByName"))
			.queryParam("name", name);

		return getCollection(uriBuilder.toUriString());
	}

	@Override
	protected String getId(Account entity) {
		return String.valueOf(entity.getId());
	}
}
