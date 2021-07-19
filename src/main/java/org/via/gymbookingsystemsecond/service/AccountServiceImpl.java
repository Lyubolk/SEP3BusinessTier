package org.via.gymbookingsystemsecond.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.via.gymbookingsystemsecond.hateoas.AccountHateoasRepository;
import org.via.gymbookingsystemsecond.hateoas.PasswordHateoasRepository;
import org.via.gymbookingsystemsecond.service.dto.AccountDTO;
import org.via.gymbookingsystemsecond.service.dto.CreateAccountRequest;
import org.via.gymbookingsystemsecond.service.dto.PasswordDTO;

import java.util.Optional;

@Component
public class AccountServiceImpl implements AccountService{

    @Autowired
    private AccountHateoasRepository accountRepository;

    @Autowired
    private PasswordHateoasRepository passwordRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Optional<AccountDTO> register(CreateAccountRequest dto) {
        AccountDTO account = new AccountDTO(dto.getUsername(), dto.getFirstName(), dto.getLastName(),
                dto.getEmail());
        account = accountRepository.add(account).orElseThrow();

        PasswordDTO pass = new PasswordDTO(account.getId(),
                passwordEncoder.encode(dto.getPassword()));
        passwordRepository.add(pass);
        return Optional.of(account);
    }
}
