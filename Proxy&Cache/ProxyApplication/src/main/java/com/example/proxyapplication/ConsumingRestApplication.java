package com.example.proxyapplication;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ConsumingRestApplication implements CommandLineRunner {

    @Autowired
    public RestTemplate restTemplate;

    public static void main(String[] args) {
        SpringApplication.run(ConsumingRestApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        String url = "http://localhost:8080/customers"; // URL del endpoint de la otra aplicación
        ResponseEntity<List<Customer>> response = restTemplate.exchange(
            url,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<List<Customer>>() {}
        );
        List<Customer> customers = response.getBody();
        customers.forEach(customer -> System.out.println(customer));
    }
}