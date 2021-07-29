package org.via.gymbookingsystemsecond.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GymDTO {
    private Long id;

    private String name;

    private int hourlyLimit;

    public GymDTO(String name, int hourlyLimit) {
        this.name = name;
        this.hourlyLimit = hourlyLimit;
    }
}
