package org.via.gymbookingsystemsecond.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.via.gymbookingsystemsecond.domain.Account;
import org.via.gymbookingsystemsecond.domain.Gym;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingDTO {
    private Account account;

    private Gym gym;

    private LocalDate date;

    private int hour;
}
