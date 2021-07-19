package org.via.gymbookingsystemsecond.hateoas;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Component
public class HateoasDataClient {

    @Autowired
    @Getter
    private RestTemplate rest;


    public <T> Optional<EntityModel<T>> get(String path, ParameterizedTypeReference<EntityModel<T>> ptr) {
        return Optional.of(rest.exchange(path, HttpMethod.GET, null, ptr).getBody());
    }

    public <F,T> Optional<EntityModel<T>> post(String path, F obj, ParameterizedTypeReference<EntityModel<T>> ptr) {
        return Optional.of(rest.exchange(path, HttpMethod.POST, new HttpEntity<F>(obj), ptr).getBody());
    }

}
