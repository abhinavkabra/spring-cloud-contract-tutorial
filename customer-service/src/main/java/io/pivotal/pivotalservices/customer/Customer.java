package io.pivotal.pivotalservices.customer;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Customer {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @ApiModelProperty(hidden=true)
    private String id;

    @ApiModelProperty(example = "Matt")
    private String firstName;

    @ApiModelProperty(example = "Campbell")
    private String lastName;

    @ApiModelProperty(example = "matt@example.com")
    private String email;

}
