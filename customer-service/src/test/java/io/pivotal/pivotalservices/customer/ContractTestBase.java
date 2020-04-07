package io.pivotal.pivotalservices.customer;


import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.contract.verifier.messaging.boot.AutoConfigureMessageVerifier;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMessageVerifier
public class ContractTestBase {

    @Autowired
    WebApplicationContext context;

    @Autowired
    CustomerServiceApi customerApi;

    @MockBean
    CustomerRepository repository;

    @Before
    public void setup() {
        RestAssuredMockMvc.webAppContextSetup(context);

        Customer customer1 = new Customer();
        customer1.setId(UUID.randomUUID().toString());
        customer1.setFirstName("Bob");
        customer1.setLastName("Ross");
        customer1.setCreatedOn("2018-08-01T01:33:16+00:00");

        Customer customer2 = new Customer();
        customer2.setId(UUID.randomUUID().toString());
        customer2.setFirstName("Joss");
        customer2.setLastName("Whedon");
        customer2.setCreatedOn("2018-08-01T12:33:16+00:00");

        when(repository.findAll()).thenReturn(Arrays.asList(customer1, customer2));
        when(repository.findById(anyString())).thenReturn(Optional.of(customer1));
    }

    protected void newCustomerAddedTriggered() {
        customerApi.publishNewCustomerAddedMessage(new NewCustomerAddedMessage(UUID.randomUUID().toString()));
    }

}
