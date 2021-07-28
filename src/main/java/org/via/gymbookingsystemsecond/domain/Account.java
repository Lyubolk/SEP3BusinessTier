package org.via.gymbookingsystemsecond.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Account {

	private Long id;

	@NonNull private String email;

	@NonNull private String name;

	@NonNull private String password;
}
