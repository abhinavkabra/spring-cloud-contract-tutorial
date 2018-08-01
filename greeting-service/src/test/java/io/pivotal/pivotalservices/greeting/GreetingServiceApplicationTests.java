package io.pivotal.pivotalservices.greeting;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
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
		DateTimeFormatter parser = ISODateTimeFormat.dateTimeNoMillis();
		String jtdate = "2018-08-01T06:00:00+00:00";
		DateTime lastPollTime = parser.parseDateTime(jtdate);
		customerService.setLastPollTime(lastPollTime.toDate());

		Date newCustomerCreatedOn = parser.parseDateTime("2018-08-01T06:33:16+00:00").toDate();

		// when
		List<Customer> newCustomers = customerService.getNewCustomers();

		// then
		assertThat(newCustomers.size(), equalTo(1));
		assertThat(newCustomers.get(0).getFirstName(), equalTo("Shaun"));
		assertThat(newCustomers.get(0).getLastName(), equalTo("Anderson"));
		assertThat(newCustomers.get(0).getCreatedOn(), equalTo(newCustomerCreatedOn));
	}

}
