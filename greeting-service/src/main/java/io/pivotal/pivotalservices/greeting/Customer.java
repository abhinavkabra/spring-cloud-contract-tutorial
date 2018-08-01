package io.pivotal.pivotalservices.greeting;

import lombok.Data;

@Data
public class Customer {

    private String firstName;
    private String lastName;
    private String email;
    private String createdOn;

}
