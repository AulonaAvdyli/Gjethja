package com.katrasolutions.feedbackservice.request;

import javax.validation.constraints.NotBlank;
import java.util.Date;

public class FeedbackRequest {

    private Long id;

    @NotBlank(message = "description must not be empty")
    private String description;

    private Date createdOn;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

}
