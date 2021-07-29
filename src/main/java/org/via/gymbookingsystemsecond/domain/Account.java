package org.via.gymbookingsystemsecond.domain;

import lombok.*;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Account {

	private Long id;

	@NonNull private String email;

	@NonNull private String name;

	@NonNull private String password;
}
