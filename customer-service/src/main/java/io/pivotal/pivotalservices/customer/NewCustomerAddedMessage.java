package io.pivotal.pivotalservices.customer;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NewCustomerAddedMessage {

    private String customerId;

}
