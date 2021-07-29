package org.via.gymbookingsystemsecond.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.via.gymbookingsystemsecond.domain.Account;
import org.via.gymbookingsystemsecond.service.AccountService;
import org.via.gymbookingsystemsecond.service.dto.AccountDTO;
import org.via.gymbookingsystemsecond.service.dto.CreateAccountDTO;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    @Autowired
    AccountService service;

    @PostMapping("/register")
    public AccountDTO register(@RequestBody CreateAccountDTO dto) {
        return service.register(dto);
    }
}
