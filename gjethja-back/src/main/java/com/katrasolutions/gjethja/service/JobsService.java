package com.katrasolutions.gjethja.service;

import com.katrasolutions.gjethja.model.JobsModel;

import java.util.List;

public interface JobsService {

    JobsModel getJobById(Long id);

    List<JobsModel> getAll();

    JobsModel create(JobsModel jobsModel);

    JobsModel update(Long id, JobsModel jobsModel);

    void delete(Long id);
}
