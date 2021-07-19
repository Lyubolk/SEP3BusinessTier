package org.via.gymbookingsystemsecond.service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class PasswordDTO {

    @NonNull
    private String user;

    @NonNull
    private String password;

}
