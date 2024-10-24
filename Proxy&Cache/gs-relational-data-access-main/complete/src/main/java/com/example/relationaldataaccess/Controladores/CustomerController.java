package com.example.relationaldataaccess.Controladores;

import Modelos.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

@RestController
public class CustomerController {

    private static final Logger log = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Cacheable("customers")
    @GetMapping("/customers")
    public List<Customer> getCustomers() {
        long startTime = System.currentTimeMillis();
        List<Customer> customers = jdbcTemplate.query(
                "SELECT id, first_name, last_name FROM customers",
                (rs, rowNum) -> new Customer(rs.getLong("id"), rs.getString("first_name"), rs.getString("last_name"))
        );
        long endTime = System.currentTimeMillis();
        log.info("Request processed in {} ms", (endTime - startTime));
        return customers;
    }


}