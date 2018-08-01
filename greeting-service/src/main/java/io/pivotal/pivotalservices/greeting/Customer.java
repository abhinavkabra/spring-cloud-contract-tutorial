package io.pivotal.pivotalservices.greeting;

import lombok.Data;

import java.util.Date;

@Data
public class Customer {

    private String firstName;
    private String lastName;
    private String email;
    private Date createdOn;

}
