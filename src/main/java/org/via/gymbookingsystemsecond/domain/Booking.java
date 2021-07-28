package org.via.gymbookingsystemsecond.domain;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Booking {

	private Account account;

	private Gym gym;

	private LocalDate date;

	private int hour;
}
