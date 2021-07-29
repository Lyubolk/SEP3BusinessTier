package org.via.gymbookingsystemsecond.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.via.gymbookingsystemsecond.domain.Booking;
import org.via.gymbookingsystemsecond.persistence.hateoas.HateoasAccountRepository;
import org.via.gymbookingsystemsecond.persistence.hateoas.HateoasBookingRepository;
import org.via.gymbookingsystemsecond.persistence.hateoas.HateoasGymRepository;
import org.via.gymbookingsystemsecond.service.dto.BookingDTO;
import org.via.gymbookingsystemsecond.service.dto.CreateBookingDTO;

@Component
public class BookingServiceImpl implements BookingService{
    @Autowired
    HateoasBookingRepository bookingRepository;
    @Autowired
    HateoasGymRepository gymRepository;
    @Autowired
    HateoasAccountRepository accountRepository;

    @Override
    public BookingDTO createBooking(CreateBookingDTO dto) {
        Booking booking = new Booking(accountRepository.findById(dto.getAccountId()).orElseThrow(),
                gymRepository.findById(dto.getGymId()).orElseThrow(), dto.getDate(), dto.getHour());

        booking = bookingRepository.add(booking);
        return new BookingDTO(booking.getAccount(), booking.getGym(), booking.getDate(), booking.getHour());
    }
}
