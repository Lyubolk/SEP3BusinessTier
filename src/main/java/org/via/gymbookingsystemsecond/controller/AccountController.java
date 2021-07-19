package org.via.gymbookingsystemsecond.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.via.gymbookingsystemsecond.service.AccountService;
import org.via.gymbookingsystemsecond.service.dto.AccountDTO;
import org.via.gymbookingsystemsecond.service.dto.CreateAccountRequest;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService service;

    @PostMapping("/register")
    public AccountDTO register(@RequestBody CreateAccountRequest dto) {
        return service.register(dto).orElseThrow();
    }
}
