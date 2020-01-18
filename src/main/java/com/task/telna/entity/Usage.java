package com.task.telna.entity;

import com.task.telna.enums.UsageType;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@ApiOperation(value = "usage",
        notes = "Contains usage information about users.")
public class Usage {

    @Id
    @GeneratedValue
    @ApiModelProperty(notes = "Primary key of Usage.", name = "usageId", required = true)
    private long usageId;

    @ApiModelProperty(notes = "Type of usage.", name = "usageType", required = true)
    private UsageType usageType;

    @Temporal(TemporalType.DATE)
    @ApiModelProperty(notes = "Enter in	the	date, month, year of usage.", name = "startDate", required = true)
    @NotNull
    private Date startDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @ApiModelProperty(notes = "User", name = "user", required = true)
    @NotNull
    private User user;

    public Usage() {
        super();
    }

    public Usage(UsageType usageType, Date startDate, User user) {
        super();
        this.usageType = usageType;
        this.startDate = startDate;
        this.user = user;
    }

    public long getUsageId() {
        return usageId;
    }

    public UsageType getUsageType() {
        return usageType;
    }

    public Date getStartDate() {
        return startDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
