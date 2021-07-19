package org.via.gymbookingsystemsecond.service.dto;

import lombok.*;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class CreateAccountRequest {
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
