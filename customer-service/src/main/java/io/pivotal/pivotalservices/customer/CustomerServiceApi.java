package io.pivotal.pivotalservices.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/customers")
public class CustomerServiceApi {

    @Autowired
    private CustomerRepository repository;

    @GetMapping
    public Iterable<Customer> getCustomers() {
        return repository.findAll();
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String addCustomer(@RequestBody Customer customer) {
        repository.save(customer);
        return "{ \"status\": \"OK\" }";
    }

}
