package com.example.persistenciajpa;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @SpringBoot conecta automáticamente el bean de repository existente para su uso dentro del PlaneFinderPoller bean
 * @RequiredArgsConstructor  crea el constructor para la inyeccion de dependencia de repository
 */
@EnableScheduling
@Component
@RequiredArgsConstructor //Lombok crea un constructor con un AircraftRepository como parámetro x mi
public class PlaneFinderPoller {
    private final AircraftRepository repository;
    private WebClient client = WebClient.create("http://localhost:7634/aircraft");

    @Scheduled(fixedRate = 1000)
    private void pollPlanes() {
        //repository.deleteAll();

        /*client.get()
                .retrieve()
                .bodyToFlux(Aircraft.class)
                .filter(data -> !data.getReg().isEmpty())
                .toStream()
                .forEach(repository::save);*/

        repository.findAll().forEach(System.out::println);
    }
}


