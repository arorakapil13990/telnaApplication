package com.task.telna.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.task.telna.constants.Constants;
import com.task.telna.validator.PhoneNumber;
import com.task.telna.validator.UserName;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import java.util.List;

@Entity
@ApiOperation(value = "user", notes = "Contains information about users.")
public class User {

    @Id
    @GeneratedValue
    @ApiModelProperty(notes = "Primary key of User.", name = "userId", required = true)
    private long userId;

    @ApiModelProperty(notes = "Name of user.", name = "name", required = true)
    @UserName
    private String name;

    @ApiModelProperty(notes = "Email Id of user.", name = "email", required = true)
    @Email(message = Constants.USER_EMAIL_ERROR_MESSAGE)
    private String email;

    @ApiModelProperty(notes = "Phone Number of user.", name = "phoneNumber", required = true)
    @PhoneNumber
    private String phoneNumber;

    @ApiModelProperty(notes = "Country of user.", name = "country", required = true)
    private String country;

    @OneToMany
    @JsonIgnore
    @ApiModelProperty(notes = "Usages of user", name = "usages", required = true)
    private List<Usage> usages;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<Usage> getUsages() {
        return usages;
    }

    public void setUsages(List<Usage> usages) {
        this.usages = usages;
    }

}
