package org.via.gymbookingsystemsecond.service;

import org.via.gymbookingsystemsecond.service.dto.AccountDTO;
import org.via.gymbookingsystemsecond.service.dto.CreateAccountRequest;

import java.util.Optional;

public interface AccountService {
    Optional<AccountDTO> register(CreateAccountRequest dto);
}
