package com.katrasolutions.paymentservice.model;

import java.time.LocalDate;
import java.util.Date;

public class PostsModel {

    private Long id;

    private String title;

    private String description;

    private Date createdOn;

    private String status;

    private String user;

    private Boolean isBoosted;

    private LocalDate duration;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Boolean getBoosted() {
        return isBoosted;
    }

    public void setBoosted(Boolean boosted) {
        isBoosted = boosted;
    }

    public LocalDate getDuration() {
        return duration;
    }

    public void setDuration(LocalDate duration) {
        this.duration = duration;
    }
}
