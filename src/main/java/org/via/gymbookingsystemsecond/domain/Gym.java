package org.via.gymbookingsystemsecond.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Gym {

	private Long id;

	private String name;

	private int hourlyLimit;

	public Gym(String name, int hourlyLimit) {
		this.name = name;
		this.hourlyLimit = hourlyLimit;
	}
}
