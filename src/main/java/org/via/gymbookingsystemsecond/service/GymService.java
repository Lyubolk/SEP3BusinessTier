package org.via.gymbookingsystemsecond.service;

import org.via.gymbookingsystemsecond.service.dto.CreateGymDTO;
import org.via.gymbookingsystemsecond.service.dto.GymDTO;

public interface GymService {
    GymDTO createGym(CreateGymDTO dto);
}
