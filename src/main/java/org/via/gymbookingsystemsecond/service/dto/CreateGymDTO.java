package org.via.gymbookingsystemsecond.service.dto;

import lombok.Data;

@Data
public class CreateGymDTO {
    private String name;

    private int hourlyLimit;
}
