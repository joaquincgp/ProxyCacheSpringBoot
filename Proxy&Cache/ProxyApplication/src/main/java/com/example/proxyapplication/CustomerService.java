package com.example.proxyapplication;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private RestTemplate restTemplate;

    public List<Customer> getCustomers() {
        String url = "http://localhost:8080/customers"; // el endpoint
        Customer[] customersArray = restTemplate.getForObject(url, Customer[].class);
        return Arrays.asList(customersArray);
    }
}
