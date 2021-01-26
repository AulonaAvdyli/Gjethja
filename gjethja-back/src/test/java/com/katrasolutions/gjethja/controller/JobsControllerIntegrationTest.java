package com.katrasolutions.gjethja.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.katrasolutions.gjethja.entity.Jobs;
import com.katrasolutions.gjethja.exception.CustomExceptionHandler;
import com.katrasolutions.gjethja.model.JobsModel;
import com.katrasolutions.gjethja.repository.JobsRepository;
import com.katrasolutions.gjethja.service.JobsService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JobsControllerIntegrationTest {

    private static final ObjectMapper om = new ObjectMapper();

    private static final String DEFAULT_HOUSEKEEPER = "housekeeper";
    private static final String BABYSITTER = "babysitter";
    private static final String ELDERCARE = "eldercare";
    private static final String PETCARE = "petcare";
    private static final String NURSE = "nurse";

    private static final String JOB_UPDATED = "tutor";

    @Autowired
    private JobsService jobsService;

    @Autowired
    private JobsRepository jobsRepository;

    @Autowired
    private CustomExceptionHandler customExceptionHandler;

    @Autowired
    private Validator validator;

    @Autowired
    private EntityManager em;

    private MockMvc mockMvc;

    private Jobs jobs;

    @Before
    public void initTest() {
        jobs = createEntity(em);
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        JobsController jobsController = new JobsController(jobsService);
        this.mockMvc = MockMvcBuilders.standaloneSetup(jobsController)
                .setControllerAdvice(customExceptionHandler)
                .setValidator(validator).build();
    }

    private static Jobs createEntity(EntityManager em) {
        Jobs jobs = new Jobs();
        jobs.setJobName(DEFAULT_HOUSEKEEPER);
        return jobs;
    }

    @Test
    @Transactional
    public void getJobById() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/jobs/{id}", jobs.getId()))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].jobName").value(DEFAULT_HOUSEKEEPER))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].jobName").value(BABYSITTER))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[2].jobName").value(ELDERCARE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[3].jobName").value(PETCARE));

    }

    @Test
    public void findJobsWithNoExistingId() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/jobs/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void getAllJobs() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/jobs"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].jobName").value(DEFAULT_HOUSEKEEPER))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].jobName").value(BABYSITTER))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[2].jobName").value(ELDERCARE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[3].jobName").value(PETCARE));
    }

    @Test
    @WithMockUser("test@test.com")
    @Transactional
    public void createJobs() throws Exception {

        JobsModel jobsModel = new JobsModel();
        jobsModel.setJobName(NURSE);

        mockMvc.perform(post("/jobs")
                .content(om.writeValueAsString(jobsModel))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.jobName").value(NURSE));
    }

    @Test
    @WithMockUser("test@test.com")
    @Transactional
    public void updateJobs() throws Exception {

        JobsModel jobsModel = new JobsModel();
        jobsModel.setJobName(JOB_UPDATED);

        Jobs jobs = jobsRepository.saveAndFlush(this.jobs);

        mockMvc.perform(put("/jobs/{id}", jobs.getId())
                .content(om.writeValueAsString(jobsModel))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.jobName").value(JOB_UPDATED));
    }

    @Test
    @WithMockUser("test@test.com")
    @Transactional
    public void deleteJobs() throws Exception {

        Jobs job = jobsRepository.saveAndFlush(jobs);
        mockMvc.perform(delete("/jobs/{id}", job.getId()))
                .andExpect(status().isNoContent());
    }
}
