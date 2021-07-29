package org.via.gymbookingsystemsecond.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.via.gymbookingsystemsecond.util.RandomUtils.*;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.test.web.servlet.MockMvc;
import org.via.gymbookingsystemsecond.domain.Account;
import org.via.gymbookingsystemsecond.domain.Booking;
import org.via.gymbookingsystemsecond.domain.Gym;
import org.via.gymbookingsystemsecond.service.dto.BookingDTO;
import org.via.gymbookingsystemsecond.service.dto.CreateBookingDTO;

@SpringBootTest
@AutoConfigureMockMvc
public class BookingRepositoryTests {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private BookingRepository repo;
    @Autowired
    private GymRepository gymRepo;
    @Autowired
    private AccountRepository accRepo;

    @Test
    void testCreateBooking() throws Exception {
        Account acc = mapper. readValue(mockMvc.perform(post("/accounts/register")
                .contentType("application/json")
                .content(mapper.writeValueAsString(randAccount())))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString(), Account.class);

        Gym gym = mapper.readValue(
                mockMvc.perform(
                        post("/gyms/createGym")
                                .contentType("application/json")
                                .content(mapper.writeValueAsString(randGym()))
                ).andExpect(status().isCreated())
                        .andReturn()
                        .getResponse()
                        .getContentAsString()
                , Gym.class);

        CreateBookingDTO b = new CreateBookingDTO(acc.getId(), gym.getId(), LocalDate.now(), 11);

        BookingDTO booking = mapper.readValue(
                mockMvc.perform(
                        post("/bookings/createBooking")
                                .contentType("application/json")
                                .content(mapper.writeValueAsString(b))
                ).andExpect(status().isOk())
                        .andReturn()
                        .getResponse()
                        .getContentAsString()
                , BookingDTO.class);

        assertEquals(b.getAccountId(), booking.getAccount().getId());
        assertEquals(b.getGymId(), booking.getGym().getId());
        assertEquals(b.getDate(), booking.getDate());
        assertEquals(b.getHour(), booking.getHour());
    }

    @Test
    void testFindByAccountIdAndGymId() {
        Account a = accRepo.add(randAccount());
        Gym g = gymRepo.add(randGym());

        Booking b = repo.add(new Booking(
                a, g, LocalDate.now(), getRandom().nextInt(24)));

        List<Booking> resp = repo.findByAccountIdAndGymId(
                b.getAccount().getId(),
                b.getGym().getId());

        assertTrue(resp.contains(b));
    }

    @Test
    void testFindByAccountIdAndGymIdAndDate() {
        Account a = accRepo.add(randAccount());
        Gym g = gymRepo.add(randGym());

        Booking b = repo.add(new Booking(
                a, g, LocalDate.now(), getRandom().nextInt(24)));

        System.out.println("################################################");

        List<Booking> resp = repo.findByAccountIdAndGymIdAndDate(
                b.getAccount().getId(),
                b.getGym().getId(),
                b.getDate());

        System.out.println("################################################");
        System.out.println(b);
        System.out.println("################################################");
        System.out.println(resp);
        System.out.println("################################################");

        assertTrue(resp.contains(b));
    }

    @Test
    void testFindByGymIdAndDateAndHour() {
        Account a = accRepo.add(randAccount());
        Gym g = gymRepo.add(randGym());

        Booking b = repo.add(new Booking(
                a, g, LocalDate.now(), getRandom().nextInt(24)));

        List<Booking> resp = repo.findByGymIdAndDateAndHour(
                b.getGym().getId(),
                b.getDate(),
                b.getHour());

        assertTrue(resp.contains(b));
    }

    @Test
    void testCountByGymIdAndDateAndHour() {
        Account a = accRepo.add(randAccount());
        Gym g = gymRepo.add(randGym());

        Booking b = repo.add(new Booking(
                a, g, LocalDate.now(), getRandom().nextInt(24)));

        int resp = repo.countByGymIdAndDateAndHour(
                b.getGym().getId(),
                b.getDate(),
                b.getHour());

        List<Booking> all = repo.findByGymIdAndDateAndHour(
                b.getGym().getId(),
                b.getDate(),
                b.getHour());

        assertEquals(all.size(), resp);
    }
}
