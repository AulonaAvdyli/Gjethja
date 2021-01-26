package com.katrasolutions.gjethja.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "providers")
public class Provider extends User {

    @Column(name = "rate")
    private String rate;

    @Column(name = "education")
    private String education;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "provider")
    private Set<Certificate> certificates = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "provider_jobs",
            inverseJoinColumns = @JoinColumn(name = "job_id", referencedColumnName = "job_id", nullable = false),
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false))
    private List<Jobs> jobs = new ArrayList<>();

    public List<Jobs> getJobs() {
        return jobs;
    }

    public void setJobs(List<Jobs> jobs) {
        this.jobs = jobs;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public Set<Certificate> getCertificates() {
        return certificates;
    }

    public void setCertificates(Set<Certificate> certificates) {
        this.certificates = certificates;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }
}
