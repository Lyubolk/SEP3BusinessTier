package org.via.gymbookingsystemsecond.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.via.gymbookingsystemsecond.service.BookingService;
import org.via.gymbookingsystemsecond.service.dto.BookingDTO;
import org.via.gymbookingsystemsecond.service.dto.CreateBookingDTO;

@RestController
@RequestMapping("/bookings")
public class BookingController {
    @Autowired
    BookingService service;

    @PostMapping("/createBooking")
    public BookingDTO createBooking(@RequestBody CreateBookingDTO dto) {
        return service.createBooking(dto);
    }
}
