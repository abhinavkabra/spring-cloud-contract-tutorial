package io.pivotal.pivotalservices.greeting;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureStubRunner(ids = {"io.pivotal.pivotalservices:customer-service:+:stubs:9090"}, workOffline = true)
public class GreetingServiceApplicationTests {

	@Autowired
	private CustomerService customerService;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testCustomerServiceGetsNewCustomers() {
		// given

		// when
		List<Customer> newCustomers = customerService.getNewCustomers();

		// then
		assertThat(newCustomers.size(), equalTo(2));
		assertThat(newCustomers.get(0).getFirstName(), equalTo("Matt"));
		assertThat(newCustomers.get(0).getLastName(), equalTo("Campbell"));
		assertThat(newCustomers.get(0).getCreatedOn(), equalTo("2018-08-01T05:33:16+00:00"));
	}

}
