package com.katrasolutions.gjethja.request;

import javax.validation.constraints.NotNull;
import java.util.List;

public class  ProviderRegisterRequest extends RegisterRequest {

    @NotNull(message = "Jobs must not be null")
    private List<String> jobs;

    @NotNull(message = "Education must not be empty")
    private String education;

    public List<String> getJobs() {
        return jobs;
    }

    public void setJobs(List<String> jobs) {
        this.jobs = jobs;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }
}
