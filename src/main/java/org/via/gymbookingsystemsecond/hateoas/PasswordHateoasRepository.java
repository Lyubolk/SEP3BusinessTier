package org.via.gymbookingsystemsecond.hateoas;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;
import org.via.gymbookingsystemsecond.service.dto.PasswordDTO;

import java.util.Optional;

@Component
public class PasswordHateoasRepository extends HateoasRepository<PasswordDTO, PasswordDTO> {

    public PasswordHateoasRepository() {
        super("/passwords",
                new ParameterizedTypeReference<EntityModel<PasswordDTO>>() {},
                new ParameterizedTypeReference<CollectionModel<EntityModel<PasswordDTO>>>() {});
    }

    public Optional<PasswordDTO> findByUsername(String name) {
        return get("/search/findByUsername?projection=userWithPassword&name=" + name);
    }

    public Optional<PasswordDTO> findByUser(String user) {
        return get("/search/findByUser?projection=userWithPassword&user=" + user);
    }
}
