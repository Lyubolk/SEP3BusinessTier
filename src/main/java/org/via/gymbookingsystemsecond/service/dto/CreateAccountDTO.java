package org.via.gymbookingsystemsecond.service.dto;

import lombok.Data;

@Data
public class CreateAccountDTO {
    private String email;

    private String name;

    private String password;
}
