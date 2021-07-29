package org.via.gymbookingsystemsecond.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.via.gymbookingsystemsecond.domain.Account;
import org.via.gymbookingsystemsecond.domain.Gym;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class CreateBookingDTO {
    private Long accountId;

    private Long gymId;

    private LocalDate date;

    private int hour;
}
