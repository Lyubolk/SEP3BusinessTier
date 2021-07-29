package org.via.gymbookingsystemsecond.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.via.gymbookingsystemsecond.domain.Gym;
import org.via.gymbookingsystemsecond.persistence.GymRepository;
import org.via.gymbookingsystemsecond.persistence.hateoas.HateoasGymRepository;
import org.via.gymbookingsystemsecond.service.dto.CreateGymDTO;
import org.via.gymbookingsystemsecond.service.dto.GymDTO;

@Component
public class GymServiceImpl implements GymService {
    @Autowired private HateoasGymRepository repository;

    @Override
    public GymDTO createGym(CreateGymDTO dto) {
        Gym gym = new Gym(dto.getName(), dto.getHourlyLimit());
        gym = repository.add(gym);
        return new GymDTO(gym.getId(), gym.getName(), gym.getHourlyLimit());
    }
}
