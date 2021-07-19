package org.via.gymbookingsystemsecond.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.via.gymbookingsystemsecond.hateoas.AccountHateoasRepository;
import org.via.gymbookingsystemsecond.hateoas.HateoasDataClient;
import org.via.gymbookingsystemsecond.hateoas.PasswordHateoasRepository;
import org.via.gymbookingsystemsecond.service.dto.AccountDetailsDTO;
import org.via.gymbookingsystemsecond.service.dto.PasswordDTO;

@Service
public class PasswordService implements UserDetailsService {

    @Autowired
    private AccountHateoasRepository accountRepository;

    @Autowired
    private PasswordHateoasRepository passwordRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return accountRepository.findByName(username)
                .flatMap(account ->
                        passwordRepository.findByUsername(username)
                                .map(PasswordDTO::getPassword)
                                .map(pass -> new AccountDetailsDTO(account, pass)))
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));
    }
}
