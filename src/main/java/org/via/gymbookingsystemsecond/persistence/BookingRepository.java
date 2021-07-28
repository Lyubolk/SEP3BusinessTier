package org.via.gymbookingsystemsecond.persistence;

import org.via.gymbookingsystemsecond.domain.Booking;

import java.time.LocalDate;
import java.util.List;

public interface BookingRepository extends Repository<Booking> {

	List<Booking> findByAccountIdAndGymId(
			Long accountId, Long gymId);

	List<Booking> findByAccountIdAndGymIdAndDate(
			Long accountId, Long gymId, LocalDate date);

	List<Booking> findByGymIdAndDateAndHour(
			Long gymId, LocalDate date, int hour);

	int countByGymIdAndDateAndHour(
			Long gymId, LocalDate date, int hour);
}
