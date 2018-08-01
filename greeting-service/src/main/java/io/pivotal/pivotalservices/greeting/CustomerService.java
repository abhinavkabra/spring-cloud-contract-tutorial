package io.pivotal.pivotalservices.greeting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
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
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON_UTF8));
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<Customer[]> response = restTemplate.exchange("http://localhost:9090/customers", HttpMethod.GET, entity, Customer[].class);

        return Arrays.stream(response.getBody())
                .filter(c -> c.getCreatedOn().after(lastPollTime))
                .collect(Collectors.toList());
    }

    protected void setLastPollTime(Date lastPollTime) {
        this.lastPollTime = lastPollTime;
    }
}
