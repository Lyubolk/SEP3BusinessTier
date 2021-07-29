package org.via.gymbookingsystemsecond.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.via.gymbookingsystemsecond.service.GymService;
import org.via.gymbookingsystemsecond.service.dto.AccountDTO;
import org.via.gymbookingsystemsecond.service.dto.CreateGymDTO;
import org.via.gymbookingsystemsecond.service.dto.GymDTO;

@RestController
@RequestMapping("/gyms")
public class GymController {
    @Autowired
    GymService service;

    @PostMapping("/createGym")
    public ResponseEntity<GymDTO> createGym(@RequestBody CreateGymDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.createGym(dto));
    }
}
