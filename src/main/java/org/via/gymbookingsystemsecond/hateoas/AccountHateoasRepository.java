package org.via.gymbookingsystemsecond.hateoas;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;
import org.via.gymbookingsystemsecond.service.dto.AccountDTO;

import java.util.Optional;

@Component
public class AccountHateoasRepository extends HateoasRepository<AccountDTO, AccountDTO> {

    public AccountHateoasRepository() {
        super("/accounts",
                new ParameterizedTypeReference<EntityModel<AccountDTO>>() {},
                new ParameterizedTypeReference<CollectionModel<EntityModel<AccountDTO>>>() {});
    }

    public Optional<AccountDTO> findByName(String name) {
        return get("/search/findByName?name=" + name);
    }

    public Optional<AccountDTO> findByEmail(String email) {
        return get("/search/findByEmail?email=" + email);
    }

    @Override
    protected EntityModel<AccountDTO> map(EntityModel<AccountDTO> em) {
        em.getContent().setId(
                id(em.getRequiredLink("self").getHref()));
        return em;
    }
}
