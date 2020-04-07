package io.pivotal.pivotalservices.greeting;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@RestController
public class CustomerService {

    private Date lastPollTime;

    public CustomerService() {
        setLastPollTime(new Date());
    }

    @GetMapping("/customersToGreet")
    public List<Customer> getNewCustomers() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<Customer[]> response = restTemplate.exchange("http://localhost:9090/customers", HttpMethod.GET, entity, Customer[].class);

        List<Customer> newCustomers = new ArrayList<>();
        for (Customer c : response.getBody()) {
            long customerCreatedOn = c.getCreatedOn().getTime();
            long lastPollMillis = lastPollTime.getTime();
            if (customerCreatedOn > lastPollMillis) {
                newCustomers.add(c);
            }
        }

        for (Customer c : newCustomers) {
            sendEmail(c);
        }

        setLastPollTime(new Date());
        return newCustomers;
    }

    protected void setLastPollTime(Date lastPollTime) {
        this.lastPollTime = lastPollTime;
    }

    @StreamListener(Sink.INPUT)
    public void newCustomerAdded(NewCustomerEvent event) {
        // get the customer with this ID...
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<Customer> response = restTemplate.exchange("http://localhost:9090/customers/"+event.getCustomerId(), HttpMethod.GET, entity, Customer.class);

        sendEmail(response.getBody());
    }

    protected void sendEmail(Customer newCustomer) {
        System.out.println("Send To: "+newCustomer.getEmail()+";  Body: Dear "+ newCustomer.getFirstName()+", thanks for purchasing...");
    }
}
