package com.katrasolutions.gjethja.controller;

import com.katrasolutions.gjethja.model.JobsModel;
import com.katrasolutions.gjethja.service.JobsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/jobs", produces = MediaType.APPLICATION_JSON_VALUE)
public class JobsController {

    private final JobsService jobsService;

    public JobsController(JobsService jobsService) {
        this.jobsService = jobsService;
    }

    @GetMapping("/{id}")
    public JobsModel getJobById(@PathVariable Long id) {
        return jobsService.getJobById(id);
    }

    @GetMapping
    public List<JobsModel> getAll() {
        return jobsService.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public JobsModel create(@RequestBody @Valid JobsModel jobsModel) {
        return jobsService.create(jobsModel);
    }

    @PutMapping("/{id}")
    public JobsModel update(@PathVariable Long id, @RequestBody @Valid JobsModel jobsModel) {
        return jobsService.update(id, jobsModel);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        jobsService.delete(id);
    }
}
