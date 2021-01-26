package com.katrasolutions.gjethja.mapper;

import com.katrasolutions.gjethja.entity.Jobs;
import com.katrasolutions.gjethja.model.JobsModel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class JobsMapper {
    public JobsModel entityToModel(Jobs job) {
        JobsModel jobsModel = new JobsModel();
        jobsModel.setId(job.getId());
        jobsModel.setJobName(job.getJobName());
        return jobsModel;
    }

    public Jobs modelToEntity(JobsModel jobsModel) {
        Jobs jobs= new Jobs();
        jobs.setJobName(jobsModel.getJobName());
        return jobs;
    }

    public List<JobsModel> entitiesToModels(List<Jobs> jobs) {
        return jobs.stream().map(this::entityToModel).collect(Collectors.toList());
    }

    public List<Jobs> modelsToEntities(List<JobsModel> jobs) {
        return jobs.stream().map(this::modelToEntity).collect(Collectors.toList());
    }

    public Jobs updateExistingEntity(Jobs jobs, JobsModel jobsModel) {
        jobs.setJobName(jobsModel.getJobName());
        return jobs;
    }
}
