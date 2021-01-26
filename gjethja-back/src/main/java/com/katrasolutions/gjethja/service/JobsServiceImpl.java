package com.katrasolutions.gjethja.service;

import com.katrasolutions.gjethja.entity.Jobs;
import com.katrasolutions.gjethja.exception.ExceptionMessage;
import com.katrasolutions.gjethja.exception.RestApiNotFoundException;
import com.katrasolutions.gjethja.exception.RestApiUnauthorizedException;
import com.katrasolutions.gjethja.mapper.JobsMapper;
import com.katrasolutions.gjethja.model.JobsModel;
import com.katrasolutions.gjethja.repository.JobsRepository;
import com.katrasolutions.gjethja.repository.ProviderRepository;
import com.katrasolutions.gjethja.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class JobsServiceImpl implements JobsService {

    private final JobsRepository jobsRepository;
    private final JobsMapper jobsMapper;
    private final UserRepository userRepository;
    private final ProviderRepository providerRepository;

    public JobsServiceImpl(JobsRepository jobsRepository, JobsMapper jobsMapper, UserRepository userRepository, ProviderRepository providerRepository) {
        this.jobsRepository = jobsRepository;
        this.jobsMapper = jobsMapper;
        this.userRepository = userRepository;
        this.providerRepository = providerRepository;
    }

    @Override
    public JobsModel getJobById(Long id) {
        Jobs jobs = jobsRepository.findById(id)
                .orElseThrow(() -> new RestApiNotFoundException(ExceptionMessage.JOB_NOT_FOUND));
        return jobsMapper.entityToModel(jobs);
    }

    @Override
    public List<JobsModel> getAll() {
        return jobsMapper.entitiesToModels(jobsRepository.findAll());
    }

    @Override
    @Transactional
    public JobsModel create(JobsModel jobsModel) {
        Jobs jobs = jobsMapper.modelToEntity(jobsModel);
        jobsRepository.save(jobs);
        return jobsMapper.entityToModel(jobs);
    }

    @Override
    @Transactional
    public JobsModel update(Long id, JobsModel jobsModel) {
        Jobs jobs = jobsRepository.findById(id).orElseThrow(() -> new RestApiNotFoundException(ExceptionMessage.JOB_NOT_FOUND));
        jobs = jobsMapper.updateExistingEntity(jobs, jobsModel);
        jobsRepository.save(jobs);
        return jobsMapper.entityToModel(jobs);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Jobs jobs = jobsRepository.findById(id).orElseThrow(() -> new RestApiUnauthorizedException(ExceptionMessage.JOB_NOT_FOUND));
        jobsRepository.delete(jobs);
    }
}
