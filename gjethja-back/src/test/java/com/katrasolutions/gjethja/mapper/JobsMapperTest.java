package com.katrasolutions.gjethja.mapper;

import com.katrasolutions.gjethja.entity.Jobs;
import com.katrasolutions.gjethja.mapper.JobsMapper;
import com.katrasolutions.gjethja.model.JobsModel;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JobsMapperTest {
    private static final Long ID = 1L;
    private static final String JOB_NAME = "jobName";

    private JobsMapper jobsMapper = new JobsMapper();

    @Test
    public void modelToEntityTest() {
        JobsModel jobsModel = getJobsModel();
        Jobs jobs = jobsMapper.modelToEntity(jobsModel);
        assertEquals(jobs.getJobName(), JOB_NAME);
    }

    @Test
    public void entityToModelTest() {
        Jobs jobs = getJobs();
        JobsModel jobsModel = jobsMapper.entityToModel(jobs);
        assertEquals(jobsModel.getId(), ID);
        assertEquals(jobsModel.getJobName(), JOB_NAME);
    }

    @Test
    public void updateExistingEntityTest() {
        Jobs jobs = getJobs();
        JobsModel jobsModel = getJobsModel();
        jobsModel.setJobName("Test test");
        jobs = jobsMapper.updateExistingEntity(jobs, jobsModel);
        assertEquals(jobs.getJobName(), "Test test");
    }

    private static JobsModel getJobsModel() {
        JobsModel jobsModel = new JobsModel();
        jobsModel.setId(ID);
        jobsModel.setJobName(JOB_NAME);

        return jobsModel;
    }

    private static Jobs getJobs() {
        Jobs jobs = new Jobs();
        jobs.setId(ID);
        jobs.setJobName(JOB_NAME);

        return jobs;
    }
}
