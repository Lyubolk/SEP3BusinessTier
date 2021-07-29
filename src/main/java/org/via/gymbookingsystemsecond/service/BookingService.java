package org.via.gymbookingsystemsecond.service;

import org.via.gymbookingsystemsecond.service.dto.BookingDTO;
import org.via.gymbookingsystemsecond.service.dto.CreateBookingDTO;

public interface BookingService {
    BookingDTO createBooking(CreateBookingDTO dto);
}
