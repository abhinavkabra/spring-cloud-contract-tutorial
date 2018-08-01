package io.pivotal.pivotalservices.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.http.MediaType;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/customers")
public class CustomerServiceApi {

    @Autowired
    private CustomerRepository repository;

    @Autowired
    Source source;

    @GetMapping
    public Iterable<Customer> getCustomers() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable("id") String id) {
        return repository.findOne(id);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String addCustomer(@RequestBody Customer customer) {
        repository.save(customer);
        publishNewCustomerAddedMessage(new NewCustomerAddedMessage(customer.getId()));
        return "{ \"status\": \"OK\" }";
    }

    public void publishNewCustomerAddedMessage(NewCustomerAddedMessage message) {
        source.output().send(MessageBuilder.withPayload(message).build());
    }

}
