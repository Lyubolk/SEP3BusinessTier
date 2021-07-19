package org.via.gymbookingsystemsecond.hateoas;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.via.gymbookingsystemsecond.service.AccountServiceImpl;
import org.via.gymbookingsystemsecond.service.dto.AccountDTO;
import org.via.gymbookingsystemsecond.service.dto.CreateAccountRequest;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class HateoasDataClientTest {

    @Autowired
    private AccountHateoasRepository repository;

    @Test
    public void testRegisterAccount() {
        AccountDTO accountDTO = new AccountDTO("Lyubi", "Lyuboslav", "Kotsev", "lubo@gmail.com");
        CreateAccountRequest request = new CreateAccountRequest("Lyubi", "Lyuboslav", "Kotsev", "lubo@gmail.com", "pass");
        AccountDTO duplicate = repository.add(accountDTO).orElseThrow();

        assertNotNull(duplicate.getId());
        assertEquals(accountDTO.getUsername(), duplicate.getUsername());
        assertEquals(accountDTO.getFirstName(), duplicate.getFirstName());
        assertEquals(accountDTO.getLastName(), duplicate.getLastName());
        assertEquals(accountDTO.getEmail(), duplicate.getEmail());
        System.out.println(duplicate.getEmail());
    }

    private static String randStr() {
        return UUID.randomUUID().toString();
    }
}