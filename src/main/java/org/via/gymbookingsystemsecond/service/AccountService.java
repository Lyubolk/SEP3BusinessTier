package org.via.gymbookingsystemsecond.service;

import org.via.gymbookingsystemsecond.service.dto.AccountDTO;
import org.via.gymbookingsystemsecond.service.dto.CreateAccountDTO;

public interface AccountService {
    AccountDTO register (CreateAccountDTO dto);
}
