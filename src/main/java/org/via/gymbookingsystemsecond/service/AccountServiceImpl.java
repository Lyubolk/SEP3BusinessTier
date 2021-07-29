package org.via.gymbookingsystemsecond.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.via.gymbookingsystemsecond.domain.Account;
import org.via.gymbookingsystemsecond.persistence.hateoas.HateoasAccountRepository;
import org.via.gymbookingsystemsecond.service.dto.AccountDTO;
import org.via.gymbookingsystemsecond.service.dto.CreateAccountDTO;

@Component
public class AccountServiceImpl implements AccountService{

    @Autowired private HateoasAccountRepository repository;

    @Override
    public AccountDTO register(CreateAccountDTO dto) {
        Account account = new Account(dto.getEmail(), dto.getName(), dto.getPassword());
        account = repository.add(account);
        return new AccountDTO(account.getId(), account.getEmail(), account.getName(), account.getPassword());
    }
}
