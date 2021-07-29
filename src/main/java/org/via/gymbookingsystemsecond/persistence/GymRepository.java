package org.via.gymbookingsystemsecond.persistence;

import org.via.gymbookingsystemsecond.domain.Gym;

import java.util.Optional;

public interface GymRepository extends Repository<Gym> {
    Optional<Gym> findById(Long id);
}
