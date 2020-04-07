package io.pivotal.pivotalservices.greeting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    private Date lastPollTime;

    public List<Customer> getNewCustomers() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<Customer[]> response = restTemplate.exchange("http://localhost:9090/customers", HttpMethod.GET, entity, Customer[].class);

        return Arrays.stream(response.getBody())
                .filter(c -> c.getCreatedOn().after(lastPollTime))
                .map(c -> { sendEmail(c); return c; })
                .collect(Collectors.toList());
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
