package org.via.gymbookingsystemsecond;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class GymbookingsystemsecondApplication {

    public static void main(String[] args) {
        SpringApplication.run(GymbookingsystemsecondApplication.class, args);
    }

    @Bean
    public RestTemplate provideRestTemplate() {
        return new RestTemplate();
    }
}
