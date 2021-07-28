package org.via.gymbookingsystemsecond.persistence.hateoas;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;
import org.via.gymbookingsystemsecond.domain.Gym;
import org.via.gymbookingsystemsecond.persistence.GymRepository;

@Component
public class HateoasGymRepository extends HateoasRepository<Gym> implements GymRepository {

	public HateoasGymRepository() {
		super("gyms",
				new ParameterizedTypeReference<EntityModel<Gym>>() {},
				new ParameterizedTypeReference<CollectionModel<EntityModel<Gym>>>() {});
	}

	@Override
	protected String getId(Gym entity) {
		return String.valueOf(entity.getId());
	}
}
