package org.via.gymbookingsystemsecond.persistence.hateoas;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;
import org.via.gymbookingsystemsecond.domain.Booking;
import org.via.gymbookingsystemsecond.persistence.BookingRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class HateoasBookingRepository extends HateoasRepository<Booking> implements BookingRepository {

	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	public HateoasBookingRepository() {
		super("/bookings",
				new ParameterizedTypeReference<EntityModel<Booking>>() {},
				new ParameterizedTypeReference<CollectionModel<EntityModel<Booking>>>() {});
	}

	@Override
	public Booking add(Booking entity) {
		return rest.exchange(url("?projection=fullBooking"),
				HttpMethod.POST,
				new HttpEntity<>(entity),
				getTypeToken())
			.getBody()
			.getContent();
	}

	@Override
	public List<Booking> findByAccountIdAndGymId(Long accountId, Long gymId) {
		UriComponentsBuilder builder = UriComponentsBuilder
			.fromHttpUrl(url("search/findByIdAccountAndIdGym"))
			.queryParam("idAccount", accountId)
			.queryParam("idGym", gymId)
			.queryParam("projection", "fullBooking");

		return getCollection(builder.toUriString());
	}

	@Override
	public List<Booking> findByAccountIdAndGymIdAndDate(Long accountId, Long gymId, LocalDate date) {
		UriComponentsBuilder builder = UriComponentsBuilder
			.fromHttpUrl(url("search/findByIdAccountAndIdGymAndIdDate"))
			.queryParam("idAccount", accountId)
			.queryParam("idGym", gymId)
			.queryParam("idDate", date)
			.queryParam("projection", "fullBooking");

		return getCollection(builder.toUriString());
	}

	@Override
	public List<Booking> findByGymIdAndDateAndHour(Long gymId, LocalDate date, int hour) {
		UriComponentsBuilder builder = UriComponentsBuilder
			.fromHttpUrl(url("search/findByIdGymAndIdDateAndIdHour"))
			.queryParam("idGym", gymId)
			.queryParam("idDate", date)
			.queryParam("idHour", hour)
			.queryParam("projection", "fullBooking");

		return getCollection(builder.toUriString());
	}

	@Override
	public int countByGymIdAndDateAndHour(Long gymId, LocalDate date, int hour) {
		UriComponentsBuilder builder = UriComponentsBuilder
			.fromHttpUrl(url("search/countByIdGymAndIdDateAndIdHour"))
			.queryParam("idGym", gymId)
			.queryParam("idDate", date)
			.queryParam("idHour", hour)
			.queryParam("projection", "fullBooking");

		return getRest().getForObject(builder.toUriString(), Integer.class);
	}

	@Override
	protected String getId(Booking e) {
		return String.format("%d_%d_%s_%d?projection=fullBooking",
				e.getAccount(),
				e.getGym(),
				e.getDate().format(formatter),
				e.getHour());
	}
}
