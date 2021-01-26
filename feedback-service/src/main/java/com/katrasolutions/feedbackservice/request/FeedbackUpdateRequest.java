package com.katrasolutions.feedbackservice.request;

import javax.validation.constraints.NotBlank;
import java.util.Date;

public class FeedbackUpdateRequest {

    @NotBlank(message = "description must not be empty")
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
